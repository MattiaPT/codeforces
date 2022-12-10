import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		hundredsOfStepsBack(s);
	}
	
	public static void aLoveStory(Scanner s) {
		int n = s.nextInt();
		s.nextLine();
		String letter = s.nextLine();
		for (String word : letter.split(" ")) {
			calcLove(word);
		}
	}
	public static void calcLove(String word) {
		int ret = 0;
		for (int j = 0; j < word.length(); j++) {
			for (int i = j; i < word.length(); i++) {
				if (i - j < 4)
					continue;
				int been_there = 0;
				for (int a = j+1; a < i; a++) {
					if (word.substring(a, i).contains(word.substring(j, a))) {
						boolean same = true;
						boolean passed = false;
						System.out.println(been_there);
						for (int p = a; p < word.substring(a, i).indexOf(word.substring(j, a), been_there) && p + (a-j) < word.length(); p++) {
							same = same && (word.charAt(p) == word.charAt(p + (a-j)));
							passed = true;
						}
						been_there = word.substring(a, i).indexOf(word.substring(j, a), been_there);
						if (!passed || (same && word.substring(a, been_there + a).length() == word.substring(been_there + (a-j), word.length()-1).length()))
							continue;
						System.out.println(word.substring(j, a) + "  in: " + word.substring(j, i));
						ret++;
						continue;
					}
				}
			}
		}
		System.out.println(ret);
	}
	
	public static void cryptoEmpire(Scanner s) {
		TreeSet<Integer> expenses = new TreeSet<Integer>();
		int total = 0;
		int n = s.nextInt();
		s.nextLine();
		for (int i = 0; i < n; i++) {
			String line = s.nextLine();
			if (!expenses.contains(Integer.parseInt(line.split(" ")[0]))) {
				total += Integer.parseInt(line.split(" ")[1]);
				expenses.add(Integer.parseInt(line.split(" ")[0]));
			}
		}
		System.out.println(total);
	}
	
	public static void dnaPrinter(Scanner s) {
		s.nextLine();
		String dna = s.nextLine();
		double res = 0;
		for (int i = 0; i < dna.length(); i++) {
			if (dna.charAt(i) == "A".charAt(0))
				res += 45;
			else if (dna.charAt(i) == "C".charAt(0))
				res += 29;
			else if (dna.charAt(i) == "G".charAt(0))
				res += 33;
			else if (dna.charAt(i) == "T".charAt(0))
				res += 26;
		}
		System.out.println(res);
	}
	
	public static void lazyProblemSetter(Scanner s) {
		int m = s.nextInt();
		s.nextLine();
		String problem = s.nextLine();
		long total = (long) Math.pow(27, m-problem.length());
		System.out.println((total - getMultiple(problem, m) % 1000000007));
	}
	public static double getMultiple(String problem, int m) {
		if (problem.length() * 2 <= m) {
			return Math.pow(Math.pow(27, m/2 - (m - problem.length())), 2);
		}
		return 0;
	}
	
	public static void fallGuys(Scanner s) {
		int n = s.nextInt();
		String moves = "";
		s.nextLine();
		char[][] grid = new char[n][n];
		for (int i = 0; i < n; i++) {
			String line = s.nextLine();
			for (int j = 0; j < n; j++) {
				grid[i][j] = line.charAt(j);
			}
		}
		int[] pos = {0, 0};
		int[] next = getClosestBlue(grid, pos);
		while (next[0] != -Integer.MAX_VALUE) {
			while (next[0] < pos[0]) {
				moves += "U";
				pos[0]--;
				if (grid[pos[0]][pos[1]] == 'Y')
					grid[pos[0]][pos[1]] = 'B';
				else
					grid[pos[0]][pos[1]] = 'Y';
			}
			while (next[0] > pos[0]) {
				moves += "D";
				pos[0]++;
				if (grid[pos[0]][pos[1]] == 'Y')
					grid[pos[0]][pos[1]] = 'B';
				else
					grid[pos[0]][pos[1]] = 'Y';
			}
			while (next[1] < pos[1]) {
				moves += "L";
				pos[1]--;
				if (grid[pos[0]][pos[1]] == 'Y')
					grid[pos[0]][pos[1]] = 'B';
				else
					grid[pos[0]][pos[1]] = 'Y';
			}
			while (next[1] > pos[1]) {
				moves += "R";
				pos[1]++;
				if (grid[pos[0]][pos[1]] == 'Y')
					grid[pos[0]][pos[1]] = 'B';
				else
					grid[pos[0]][pos[1]] = 'Y';
			}
			next = getClosestBlue(grid, pos);
		}

		System.out.println(moves);
	}
	public static int[] getClosestBlue(char[][] grid, int[] pos) {
		ArrayList<int[]> stack = new ArrayList<>();
		stack.add(pos);
		boolean[][] visited = new boolean[grid.length][grid.length];
		while (stack.size() != 0) {
			int[] c = stack.remove(0);
			visited[c[0]][c[1]] = true;
			if (grid[c[0]][c[1]] == "B".charAt(0))
				return c;
			if (c[0]+1 < grid.length && !visited[c[0]+1][c[1]])
				stack.add(new int[] {c[0]+1, c[1]});
			if (c[1]+1 < grid.length && !visited[c[0]][c[1]+1])
				stack.add(new int[] {c[0], c[1]+1});
			if (c[0]-1 >= 0 && !visited[c[0]-1][c[1]])
				stack.add(new int[] {c[0]-1, c[1]});
			if (c[1]-1 >= 0 && !visited[c[0]][c[1]-1])
				stack.add(new int[] {c[0], c[1]-1});
		}
		return new int[] {-Integer.MAX_VALUE, -Integer.MAX_VALUE};
	}
	
	public static void incredibleEscape(Scanner s) {
		int n = s.nextInt();
		s.nextLine();
		for (int i = 0; i < n; i++) {
			int n_rooms = s.nextInt();
			int armstrech = s.nextInt();
			int fingerstrech = s.nextInt();
			s.nextLine();
			String[] buttons = s.nextLine().split(" ");
			ArrayList<int[]> adj = new ArrayList<>();
			for (int j = 0; j < buttons.length - 1; j++) {
				String[] line = s.nextLine().split(" ");
				adj.add(new int[] {Integer.parseInt(line[0]), Integer.parseInt(line[1])});
			}
			int max = DFS(buttons, adj);
		}
	}
	public static int DFS(String[] buttons, ArrayList<int[]> adj) {
		int max = 0;
		ArrayList<Integer> stack = new ArrayList<>();
		stack.add(0);
		while (stack.size() != 0) {
			int el = stack.remove(0);
		}
		return max;
	}
	public static int getButtonsPressed(String[] buttons, int[] hands) {
		int ret = 0;
		for (int h : hands) {
			ret = (Integer.parseInt(buttons[h]) == 0)? ret : ret + 1;
		}
		return ret;
	}
	
	
	public static void hundredsOfStepsBack(Scanner s) {
		int n = s.nextInt();
		int max = s.nextInt();
		s.nextLine();
		int[] standing = new int[n];
		for (int i = 0; i < n; i++)
			standing[i] = s.nextInt();
		System.out.println(testPermutation(0, standing, max));
	}
	public static boolean testPermutation(int counter, int[] standing, int max) {
		if (counter > max)
			return false;
		if (isSorted(standing))
			return true;
		boolean ret = false;
		for (int i = 0; i < standing.length - 1; i++) {
			for (int j = i+1; j < standing.length; j++) {
				swap(standing, i, j);
				ret = ret || testPermutation(counter + 1, standing, max);
				swap(standing, j, i);
			}
		}
		return ret;
	}
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static boolean isSorted(int[] arr) {
		boolean ret = true;
		for (int i = 0; i < arr.length - 1; i++)
			ret = ret && (arr[i] < arr[i + 1]);
		return ret;
	}
}

 
