package com.fruitrade.utils;

public class CommonConstants {
	
	/*
	 * 性别
	 */
	public static enum GenderType {
		MALE("男"), FEMALE("女");
		private String value;
		
		private GenderType(final String value) {
			this.value = value;
		}
		
		public String value() {
			return this.value;
		}
	}

	/*
	 * 供应商类型
	 */
	public static enum SupplyType {
		BRAND("品牌"), RETAIL("散户");
		private String value;

		private SupplyType(final String value) {
			this.value = value;
		}

		public String value() {
			return this.value;
		}
	}
}
