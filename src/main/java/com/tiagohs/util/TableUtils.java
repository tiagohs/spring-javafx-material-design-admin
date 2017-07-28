package com.tiagohs.util;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tiagohs.service.IBaseService;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;

public class TableUtils {
	
	public static final int ROW_PER_PAGE = 5;
	
	public static <P, T> void setupColumn(JFXTreeTableColumn<P, T> column, Function<P, ObservableValue<T>> mapper) {

		column.setCellValueFactory((TreeTableColumn.CellDataFeatures<P, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }
	
	public static <T, D extends RecursiveTreeObject<?>> ObservableList<D> filledDataOnTable(List<T> originalData, ITableDataCreator<D, T> onCreator) {
		final ObservableList<D> data = FXCollections.observableArrayList();
		
		originalData.forEach(d -> {
			data.add(onCreator.onCreate(d));
		});
		
		return data;
	}
	
	public static <T extends RecursiveTreeObject<T>> void configurePagination(JFXTreeTableView<T> tableView, ObservableList<T> data, Pagination pagination) {
		pagination.setPageFactory((index) -> createPage(tableView, data, pagination, index));
	}
	
	private static <T extends RecursiveTreeObject<T>> Node createPage(JFXTreeTableView<T> tableView, ObservableList<T> data, Pagination pagination, int pageIndex) {

        int fromIndex = pageIndex * ROW_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROW_PER_PAGE, data.size());
        tableView.setRoot(new RecursiveTreeItem<>(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)), RecursiveTreeObject::getChildren));
        
        pagination.setPageCount((data.size() / ROW_PER_PAGE + 1));
        pagination.setCurrentPageIndex(0);
        
        return new BorderPane();
    }

	public static <T extends RecursiveTreeObject<T>> void configureEditAndRemoveOptions(JFXTreeTableView<T> table, JFXButton edit, JFXButton remove) {
		table.setOnMouseClicked((e) -> { 
			updateEditAndRemoveButtonState(table, edit, remove);
		});
	}
	
	public static <T extends RecursiveTreeObject<T>> void updateEditAndRemoveButtonState(JFXTreeTableView<T> table, JFXButton edit, JFXButton remove) {
		if (table.getSelectionModel().selectedItemProperty().get() != null) {
			edit.setDisable(false);
			remove.setDisable(false);
		} 
	}
	
	
	public static <T extends RecursiveTreeObject<T>> void configureTableSearch(TextField textField, JFXTreeTableView<T> tableView, ITableSearchTest<T> test) {
		textField.textProperty().addListener((o, oldVal, newVal) -> {
			tableView.setPredicate(productProp -> {
                return test.onTest(productProp, newVal);
            });
        });
	}
	
	public static <T extends RecursiveTreeObject<T>> void removeItemFromTable(IBaseService<?> service, long id, JFXTreeTableView<T> table, ObservableList<T> data, StackPane container) {
		try {
			service.delete(id);
			data.remove(table.getSelectionModel().selectedItemProperty().get().getValue());
	        final IntegerProperty currCountProp = table.currentItemsCountProperty();
	        currCountProp.set(currCountProp.get() - 1);
		} catch (Exception e) {
			WindowsUtils.createDefaultDialog(container, "Error", "Error removing item, try again.", () -> {});
		}
	}
	
	public static <T extends RecursiveTreeObject<T>, D> void editItemFromTable(JFXTreeTableView<T> table, D item, String key, String pathFXML, String title, String icon) throws Exception {
		HashMap<String, D> parameters = new HashMap<String, D>();
		parameters.put(key, item);
		
		WindowsUtils.openNewWindow(pathFXML, title, icon, parameters, Modality.APPLICATION_MODAL);
	}
	
	public static void reloadTable(ITableServiceCreator creator) {
		TableService service = new TableService(creator);
		service.start();
	}

	
}
