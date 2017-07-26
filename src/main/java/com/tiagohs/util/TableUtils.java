package com.tiagohs.util;

import java.util.List;
import java.util.function.Function;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeTableColumn;

public class TableUtils {
	
	public static <P, T> void setupColumn(JFXTreeTableColumn<P, T> column, Function<P, ObservableValue<T>> mapper) {

		column.setCellValueFactory((TreeTableColumn.CellDataFeatures<P, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }
	
	@SuppressWarnings("rawtypes")
	public static <T, D extends RecursiveTreeObject> ObservableList<D> filledDataOnTable(List<T> originalData, ITableDataCreator<D, T> onCreator) {
		final ObservableList<D> data = FXCollections.observableArrayList();
		
		originalData.forEach(d -> {
			data.add(onCreator.onCreate(d));
		});
		
		return data;
	}
	
}
