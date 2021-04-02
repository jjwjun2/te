package bitcamp.api.common.enm;

import java.util.List;
import java.util.Arrays;

public enum Swear {

	KOREAN_SWEAR_LIST("SWEAR_LIST", Arrays.asList("바보", "멍청이", "담배", "욕설", "필터링", "예시", 
												  "죽음", "깡패", "부모님", "안부")),
	
	ENGLISH_SWEAR_LIST("ENGLISH_SWEAR_LIST", Arrays.asList("fuck", "ciga", "cigarette", "cigar", "father", "mother",
															"parent", "yourfather", "yourmother", "sonofbitch")),
	
	
	EXCEPTION_KOREAN_SWEAR_LIST("EXCEPTION_KOREA_SWEAR", Arrays.asList("안성기", "김바보", "박바보")),
	
	
	EXCEPTION_ENGLISH_SWEAR_LIST("EXCEPTION_KOREA_SWEAR", Arrays.asList("Johns"));
	
	
	
	private final String swear;
	private final List<String> swearList;

	Swear(String swear, List<String> swearList) {
		this.swear = swear;
		this.swearList = swearList;
	}

	public List<String> getSwearList() {
		return swearList;
	}

}