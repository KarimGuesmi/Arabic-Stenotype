
import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.nio.file.*;

class Declinations
{
	
	public static void main(String[] args) throws Exception {
		String[][] postfixes = {
			{ "PW",  "ت" },
			{ "KPW",  "تم" },
			{ "SPW",  "تنَّ" },
			{ "STK",  "ا" },
			{ "STKPW",  "تا" },
			{ "SKW",  "وا" },
			{ "S",  "نَ" },
		};

		PrintWriter pw = new PrintWriter(new File("out.JSON"), "UTF-8");
		
		pw.write("{");
		
		List<String> lines = Files.readAllLines(Paths.get(args[0]));
		lines.stream().map(line -> line.split(" ")).map(sp ->
			Arrays.stream(postfixes).map(pf -> "\"" + pf[0] + sp[0] + "\"" + ": " + "\"" + sp[1] + pf[1] + "\"" + ",").collect(Collectors.toList())
		).forEach(list -> list.stream().forEach(pw::println));

		pw.write("}");
		
		pw.flush();
		
		// To run this file
		// C:\Users\karim\Desktop>cls && "c:\Program Files\Java\jdk1.8.0_66\bin"\javac -encoding utf8 Declinations.java && java Declinations words.txt
	}
}
