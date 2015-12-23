package persistence.objects;

public class ZeroCurve {
	
	private int id;  

	//Add a date so I can keep track of a bunch of dates/history for this curve
	private double month1, month3, month6, year1, year2, year3, year5, year7, year10, year20, year30;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getMonth1() {
		return month1;
	}

	public void setMonth1(double month1) {
		this.month1 = month1;
	}

	public double getMonth3() {
		return month3;
	}

	public void setMonth3(double month3) {
		this.month3 = month3;
	}

	public double getMonth6() {
		return month6;
	}

	public void setMonth6(double month6) {
		this.month6 = month6;
	}

	public double getYear1() {
		return year1;
	}

	public void setYear1(double year1) {
		this.year1 = year1;
	}

	public double getYear2() {
		return year2;
	}

	public void setYear2(double year2) {
		this.year2 = year2;
	}

	public double getYear3() {
		return year3;
	}

	public void setYear3(double year3) {
		this.year3 = year3;
	}

	public double getYear5() {
		return year5;
	}

	public void setYear5(double year5) {
		this.year5 = year5;
	}

	public double getYear7() {
		return year7;
	}

	public void setYear7(double year7) {
		this.year7 = year7;
	}

	public double getYear10() {
		return year10;
	}

	public void setYear10(double year10) {
		this.year10 = year10;
	}

	public double getYear20() {
		return year20;
	}

	public void setYear20(double year20) {
		this.year20 = year20;
	}

	public double getYear30() {
		return year30;
	}

	public void setYear30(double year30) {
		this.year30 = year30;
	}
}
