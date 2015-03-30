package com.feytuo.bageshuo.service;

import java.util.ArrayList;
import java.util.List;

import com.feytuo.bageshuo.dao.CarouselDao;
import com.feytuo.bageshuo.domian.Carousel;

/**
 * 轮播列表操作
 * @author Tms
 */
public class CarouselService {
	CarouselDao carouselDao = new CarouselDao();

	/**
	 * 获得轮播的列表
	 * @return
	 * @throws Exception
	 */
	public List<Carousel> getCarouselList() throws Exception {
		List<Carousel> carouselList = new ArrayList<Carousel>();
		try {
			carouselList = carouselDao.queryCarouseListOrderByCaTime();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return carouselList;
	}
}
