package cir.java.FinalProject2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
//import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Questions {
	

	static List<Answer> questionList;/* = Arrays.asList(
			new Answer("Вывод текста \"Hello World\" в консоль",
					Arrays.asList("public class HelloWorld{}", "Scanner sc = new Scanner(\"Hello World\");",
							"System.out.println(\"Hello World\");", "public static void main(\"Hello World\")"),
					2,2),
			new Answer("Не является целочисленным типом", Arrays.asList("byte", "short", "long", "double"), 3,2),
			new Answer("Корректное объявление переменной",
					Arrays.asList("int 0;", "int i;", "int = 0;", "i = (int) 0;"), 1,2));*/

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		String json;
		try {
			json = mapper.writeValueAsString(questionList);
			System.out.println("JSON: " + json);
			
			PrintWriter writer = new PrintWriter("json1.txt", "UTF-8");
			writer.println(json);
			writer.close();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}

	}

}
