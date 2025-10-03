public class BS03_01_Interval {
	public static void main(String[] args) {

		Interval A = new Interval(1, 5, true, true);  // [1, 5]
		Interval B = new Interval(4, 8, true, false); // [4, 8)

		Interval C = new Interval(7, 10, true, false); // [7, 10)

		System.out.println("--- Перевірка спільних точок ---");
		System.out.println("A і B мають спільні точки: " + Interval.CheckCommonPoints(A, B)); // True
		System.out.println("A і C мають спільні точки: " + Interval.CheckCommonPoints(A, C)); // False

		Interval D = new Interval(5, 12, true, true);  // [5, 12]
		System.out.println("A і D мають спільні точки: " + Interval.CheckCommonPoints(A, D)); // True (точка 5)

		Interval E = new Interval(5, 12, false, true); // (5, 12]
		System.out.println("A і E мають спільні точки: " + Interval.CheckCommonPoints(A, E)); // False

		System.out.println("\n--- Перетин ---");
		System.out.println("Перетин [1, 5] і [4, 8): " + Interval.Intersect(A, B));  // [4, 5]
		System.out.println("Перетин [1, 5] і [7, 10): " + Interval.Intersect(A, C)); // немає спільних точок
		System.out.println("Перетин [1, 5] і [5, 12]: " + Interval.Intersect(A, D)); // [5, 5]

		System.out.println("\n--- Об'єднання ---");
		System.out.println("Об'єднання [1, 5] і [4, 8): " + Interval.Union(A, B));  // [1, 8)
		System.out.println("Об'єднання [1, 5] і [7, 10): " + Interval.Union(A, C)); // немає спільних точок
		System.out.println("Об'єднання [1, 5] і [5, 12]: " + Interval.Union(A, D)); // [1, 12]


		// Масив інтервалів
		Interval[] intervals = new Interval[]{
				new Interval(1, 5, true, true),	// Довжина: 4
				new Interval(10, 25, false, true), // Довжина: 15
				new Interval(2, 4, true, false),   // Довжина: 2
				new Interval(0, 10, true, true)	// Довжина: 10
		};

		int largestLength = Interval.findLargest(intervals);

		System.out.println("\nРозмір найбільшого інтервалу: " + largestLength); // 15
	}
}

class Interval {

	private final int start;
	private final int end;
	private final boolean includeStart;
	private final boolean includeEnd;

	public Interval(int start, int end, boolean includeStart, boolean includeEnd) {
		this.start = start;
		this.end = end;
		this.includeStart = includeStart;
		this.includeEnd = includeEnd;
	}

	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public boolean isIncludeStart() {
		return includeStart;
	}
	public boolean isIncludeEnd() {
		return includeEnd;
	}


	public static boolean CheckCommonPoints(Interval A, Interval B) {
		int maxStart = Math.max(A.start, B.start);
		int minEnd = Math.min(A.end, B.end);

		if (maxStart < minEnd) return true;

		if (maxStart == minEnd) {
			boolean A_includes = (minEnd == A.end) ? A.includeEnd : A.includeStart;
			boolean B_includes = (minEnd == B.end) ? B.includeEnd : B.includeStart;
			return A_includes && B_includes;
		}

		return false;
	}


	private static boolean endpointIncluded(int point, Interval A, Interval B, boolean isStart) {

		boolean A_includes_point = (point > A.start || (point == A.start && A.includeStart)) &&
				(point < A.end || (point == A.end && A.includeEnd));

		boolean B_includes_point = (point > B.start || (point == B.start && B.includeStart)) &&
				(point < B.end || (point == B.end && B.includeEnd));

		return A_includes_point && B_includes_point;
	}


	public static Interval Intersect(Interval A, Interval B) {
		if (!CheckCommonPoints(A, B)) {
			return new Interval(0, 0, false, false) {
				@Override public String toString() { return "немає спільних точок"; }
			};
		}

		int start = Math.max(A.start, B.start);
		int end = Math.min(A.end, B.end);
		boolean includeStart = endpointIncluded(start, A, B, true);
		boolean includeEnd = endpointIncluded(end, A, B, false);

		return new Interval(start, end, includeStart, includeEnd);
	}


	public static Interval Union(Interval A, Interval B) {
		if (!CheckCommonPoints(A, B)) {
			return new Interval(0, 0, false, false) {
				@Override public String toString() { return "немає спільних точок"; }
			};
		}

		int start = Math.min(A.start, B.start);
		int end = Math.max(A.end, B.end);

		boolean includeStart = (start == A.getStart() && A.isIncludeStart()) ||
				(start == B.getStart() && B.isIncludeStart());
		boolean includeEnd = (end == A.getEnd() && A.isIncludeEnd()) ||
				(end == B.getEnd() && B.isIncludeEnd());
		return new Interval(start, end, includeStart, includeEnd);
	}


	@Override
	public String toString() {
		return (includeStart ? "[" : "(") + start + "," + end + (includeEnd ? "]" : ")");
	}

	public int length() {
		return end - start;
	}


	public static int findLargest(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}

		int maxLen = 0;
		for (Interval interval : intervals) {
			int currLen = interval.length();

			if (currLen > maxLen) {
				maxLen = currLen;
			}
		}
		return maxLen;
	}
}