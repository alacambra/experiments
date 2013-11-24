package albert.lacambra.server.models;

public enum PeriodStep {
	 DAY(365),
	 WEEK(52),
	 MONTH(12),
	 BIMONTH(6),
	 TRIMESTER(4),
	 QUARTER(3),
	 SEMESTER(2),
	 YEAR(1);
	 
	 private int numberOfSteps;
	 
	 private PeriodStep( int numberOfSteps ) {
		 this.numberOfSteps = numberOfSteps;
	 }
}
