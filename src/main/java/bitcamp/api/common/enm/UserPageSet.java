package bitcamp.api.common.enm;

import java.util.List;
import java.util.Arrays;

public enum UserPageSet {
	BUTTON_TO_SHOW(9), INITIAL_PAGE(0), INITIAL_PAGE_SIZE(10);

	private final int number;

	UserPageSet(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

}

enum PageSizes {
	PageSizes("Select Size", Arrays.asList(5, 10, 15, 20, 25));

	private final String selectSize;
	private final List<Integer> sizes;

	PageSizes(String selectSize, List<Integer> sizes) {
		this.selectSize = selectSize;
		this.sizes = sizes;
	}

}
