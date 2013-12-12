package uberstunden;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


public class XLSBuilder {
	public static void main(String[] args) throws Throwable {
		SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
		Sheet sh = wb.createSheet();
//		for(int rownum = 0; rownum < 1000; rownum++){
//			Row row = sh.createRow(rownum);
//			for(int cellnum = 0; cellnum < 10; cellnum++){
//				Cell cell = row.createCell(cellnum);
//				String address = new CellReference(cell).formatAsString();
//				cell.setCellValue(address);
//			}
//
//		}

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		
		for (int i = 0, r = 1; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++, r++){
			
			Row row = sh.createRow(r);
			Cell cell = row.createCell(1);
			cell.setCellValue(i+1 + "/" + month + "/" + year);
			HSSFCellStyle cs = new HSSFWorkbook().createCellStyle();
			cs.setFillBackgroundColor(new HSSFColor.RED().getIndex());
			cs.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
			cell.setCellStyle(cs);
			
		}
		
		
//		calendar.get

		// Rows with rownum < 900 are flushed and not accessible
//		for(int rownum = 0; rownum < 900; rownum++){
//			Assert.assertNull(sh.getRow(rownum));
//		}

		// ther last 100 rows are still in memory
		//	        for(int rownum = 900; rownum < 1000; rownum++){
		//	            Assert.assertNotNull(sh.getRow(rownum));
		//	        }

		FileOutputStream out = new FileOutputStream("/home/albert/removeme.xlsx");
		wb.write(out);
		out.close();

		// dispose of temporary files backing this workbook on disk
		wb.dispose();
	}

	public static void main1(String[] args) {

		// represents the month (1-12)
		int month;

		// represents the year
		int year;

		// ask month and year from user
		Scanner in = new Scanner(System.in);
		System.out.print("Enter month and year: ");

		// read them as string
		String monthText = in.next();
		String yearText = in.next();

		in.close();

		try {

			// convert month and year to integer.
			// throws NumberFormatException if not convertible.
			// It would be caught below:
			month = Integer.parseInt(monthText);
			year = Integer.parseInt(yearText);

			// check if it is a valid month
			if (month < 1 || month > 12)
				throw new Exception("Invalid index for month: " + month);

			// print the calendar for the given month/year.
			printCalendarMonthYear(month, year);

		} catch (NumberFormatException e) {
			// handles NumberFormatException
			System.err.println("Number Format Error: " + e.getMessage());
		} catch (Exception e) {
			// handles any other Exception
			System.err.println(e.getMessage());
		}
	}

	/*
	 * prints a calendar month based on month / year info
	 */
	private static void printCalendarMonthYear(int month, int year) {
		// create a new GregorianCalendar object
		Calendar cal = new GregorianCalendar();

		// set its date to the first day of the month/year given by user
		cal.clear();
		cal.set(year, month - 1, 1);

		// print calendar header
		System.out.println("\n"
				+ cal.getDisplayName(Calendar.MONTH, Calendar.LONG,
						Locale.US) + " " + cal.get(Calendar.YEAR));

		// obtain the weekday of the first day of month.
		int firstWeekdayOfMonth = cal.get(Calendar.DAY_OF_WEEK);

		// obtain the number of days in month.
		int numberOfMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		// print anonymous calendar month based on the weekday of the first
		// day of the month and the number of days in month:
		printCalendar(numberOfMonthDays, firstWeekdayOfMonth);
	}

	/*
	 * 	prints an anonymous calendar month based on the weekday of the first
	 *  day of the month and the number of days in month:
	 */
	private static void printCalendar(int numberOfMonthDays, int firstWeekdayOfMonth) {

		// reset index of weekday
		int weekdayIndex = 0;

		// print calendar weekday header
		System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");

		// leave/skip weekdays before the first day of month
		for (int day = 1; day < firstWeekdayOfMonth; day++) {
			System.out.print("    ");
			weekdayIndex++;
		}

		// print the days of month in tabular format.
		for (int day = 1; day <= numberOfMonthDays; day++) {
			// print day
			System.out.printf("%1$2d", day);

			// next weekday
			weekdayIndex++;
			// if it is the last weekday
			if (weekdayIndex == 7) {
				// reset it
				weekdayIndex = 0;
				// and go to next line
				System.out.println();
			} else { // otherwise
				// print space
				System.out.print("  ");
			}
		}

		// print a final new-line.
		System.out.println();
	}
}
