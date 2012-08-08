EasyJSON
========

Java JSON encoder / decoder

Very helpful example:

class Test {
	private String bla;
	private Date myDate;
	private Boolean b;
	private List<Integer> randomList;
	private Map<Integer, String> fruits;
}


// String json
/*
{
	"bla":"Eu sou brasileiro", 
	"randomList":["43", 32, 24.9],
	"myDate":"2010-12-1", 
	"b":false,
	"fruits":{
		"12":"Unhapply i dont speak in English and the Map key must be a String :(",
		"3456":"Whatever"
	}
}*/
//Yeaaaaaaah

EasyJSON easyJSON = new EasyJSON();
Test blaaah = (Test) easyJSON.fromJson(json, Test.class);

//blaaah:
// bla = "Eu sou brasileiro"
// myDate = new Date("2010-12-1") It would be great if that methods existed AHHH MY ENGLISH
// b = false
// randomList = new ArrayList<Integer>([43, 32, 24]) I wont say the same thing twice.
// fruits = HashMap{
//   12 => "Unhapply i dont speak in English and the Map key must be a String :(",
//   3456 => "Whatever"}
// 
// Weeeeee!!! A Java Bean from a JSON. And I dont need Annotations and other configuration stuff.
// Only the Bean.class (EasyJSON is not a wizard. Almooost!)


