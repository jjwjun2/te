package bitcamp.api.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 행정구역에 따른 도시 명 분류
 - 행정구역(CityType)에 따른 도시명(CityName) 참조.
 - 행정구역이 도시 결정.
 **/
enum CityName {
    // 특별시(1) : 서울
    SEOUL("서울"),

    // 광역시(6) : 부산, 대구, 인천, 광주, 대전, 울산
    BUSAN("부산"), DAEGU("대구"), INCHEON("인천"), GWANGJU("광주"),
    DAEJEON("대전"), ULSAN("울산"),

    // 특별자치시(1) : 세종
    SEJONG_SI("세종"),

    // 도(8) : 경기도, 강원도, 충청북도, 충청남도, 전라북도, 전라남도, 경상북도, 경상남도
    GYEONGGI_DO("경기도"), GANGWON_DO("강원도"), CHUNGCHEONGBUK_DO("충청북도"),
    CHUNGCHEONGNAM_DO("충청남도"), JEOLLABUK_DO("전라북도"), JEOLLANAM_DO("전라남도"),
    GYEONGSANGBUK_DO("경상북도"), GYEONGSANGNAM_DO("경상남도"),

    // 특별자치도(1) : 제주도
    JEJU_DO("제주도");

    private final String koreanCityName;
    CityName(String koreanCityName) { this.koreanCityName = koreanCityName; }
    public String getKoreanCityName() {return koreanCityName; }
}


/*
< 연관성 있는 데이터 참조 >
보다 정확한 기능구현을 위해 enum으로 행정구역(CityType)이 도시명(CityName)을 참조하도록 했지만
사용하지 않는 관계로 아래로 내렸습니다.

https://elaw.klri.re.kr/kor_service/local.do
 */
enum CityType {
    // 특별시(1) : 서울
    SPECIAL_CITY("특별시", Arrays.asList(CityName.SEOUL)),

    // 광역시(6) : 부산, 대구, 인천, 광주, 대전, 울산
    METROPOLITAN_CITY("광역시", Arrays.asList(CityName.BUSAN, CityName.DAEGU, CityName.INCHEON, CityName.GWANGJU,
            CityName.DAEJEON, CityName.ULSAN)),

    // 특별자치시(1) : 세종
    METROPOLITAN_AUTONOMOUS_CITY("특별자치시", Arrays.asList(CityName.SEJONG_SI)),

    // 도(8) : 경기도, 강원도, 충청북도, 충청남도, 전라북도, 전라남도, 경상북도, 경상남도
    PROVINCE("도", Arrays.asList(CityName.GYEONGGI_DO, CityName.GANGWON_DO, CityName.CHUNGCHEONGBUK_DO,
            CityName.CHUNGCHEONGNAM_DO, CityName.JEOLLABUK_DO, CityName.JEOLLANAM_DO, CityName.GYEONGSANGBUK_DO,
            CityName.GYEONGSANGNAM_DO)),

    // 특별자치도(1) : 제주도
    SPECIAL_SELF_GOVERNING_PROVINCE("특별자치도", Arrays.asList(CityName.JEJU_DO));

    private final String cityType;
    private final List<CityName> cityNameList;

    CityType(String cityType, List<CityName> cityNameList) {
        this.cityType = cityType;
        this.cityNameList = cityNameList;
    }

    public String getCityType() { return cityType; }
    public List<CityName> getCityNameList() { return cityNameList; }

}


public class CityGenerator {
    public String randomCitySelect() {
        List<CityName> cities = Arrays.asList(CityName.values());
        Collections.shuffle(cities);
        return cities.get(0).getKoreanCityName();
    }


}