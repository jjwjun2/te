package bitcamp.api.common.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwearFilter {
	private ArrayList<String> swears = new ArrayList<>();
	private ArrayList<String> exceptions = new ArrayList<>();

	public SwearFilter(List<String> swears, List<String> exceptions) {
		this.swears.addAll(swears);
		this.exceptions.addAll(exceptions);
	}

	public boolean containsSwear(String keyword) {
		return true;
	}

	public static void main(String[] args) {
		SwearFilter KoreanSwearFilter = new SwearFilter(Arrays.asList("씨발", "병신", "대머리"), Arrays.asList("반병신", "쑥대머리"));
	}
}