package com.tiagohs.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Sale;
import com.tiagohs.repository.SaleRepository;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

@Service("saleService")
public class SaleServiceImpl extends BaseCrudService<Sale, JpaRepository<Sale,Long>> implements SaleService {
	
	private SaleRepository saleRepository;
	private SimpleDateFormat patternMonth;
	
	public SaleServiceImpl(SaleRepository repository) {
		super(repository);
		
		this.saleRepository = repository;
		this.patternMonth = new SimpleDateFormat("MM-yyyy");
	}
	
	public SaleRepository getSaleRepository() {
		return saleRepository;
	}

	@Override
	public javafx.concurrent.Service<List<Sale>> findAllOpenSales(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<List<Sale>>() {
			protected List<Sale> call() throws Exception {
				return saleRepository.findAllOpenSales();
			};
		}, onSucess, beforeStart);
	}

	@Override
	public javafx.concurrent.Service<List<Sale>> findAllFinalizedSales(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<List<Sale>>() {
			protected List<Sale> call() throws Exception {
				return saleRepository.findAllFinalizedSales();
			};
		}, onSucess, beforeStart);
	}

	@Override
	public javafx.concurrent.Service<Long> getTotalSales(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Long>() {
			protected Long call() throws Exception {
				return saleRepository.getTotalSales();
			};
		}, onSucess, beforeStart);
	}

	@Override
	public javafx.concurrent.Service<List<Sale>> findSaleByMonth(Calendar date, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<List<Sale>>() {
			protected List<Sale> call() throws Exception {
				if (date != null) {
					return saleRepository.findSalesByMonth(patternMonth.format(date.getTime()));
				}
				
				return null;
			};
		}, onSucess, beforeStart);
		
	}

	@Override
	public javafx.concurrent.Service<Long> getTotalSalesByMonthService(Calendar date, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Long>() {
			protected Long call() throws Exception {
				if (date != null) {
					return getTotalSalesByMonth(date);
				}
				
				return 0L;
			};
		}, onSucess, beforeStart);
	}

	@Override
	public Long getTotalSalesByMonth(Calendar date) {
		return saleRepository.getTotalSalesByMonth(patternMonth.format(date.getTime()));
	}
	
	
	
	
}
