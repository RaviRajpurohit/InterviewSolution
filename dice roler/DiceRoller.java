import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author Ravi 
 * Dice Roller: 
 * 		   Many games require players to roll dice. While Six
 *         sided dice are most common, other dice are alsoused. For instance
 *         games use 4 sided, 8 sided, 10 sided, 12, and 20 sided dice, just to
 *         name a few. The number and sides of dice to roll are indicated using
 *         the notation `nDs` where `n` is the number of dice to roll, and `s`
 *         is the number of sides on each dice. 
 *         E.g. 4D6 means "Roll four six-sided dice". Similarly 1D20 requires the player to roll one
 *         20-sided dice.
 * 
 *         The "value" of a roll is the sum of all dice rolled. For example the
 *         value of 2D6 is the sum of individual values obtained by rolling two
 *         Six-sided dice. The maximum possible value in this case is 12 (both
 *         dice rolled a 6), while the minimum possible value is 2 (both dice
 *         rolled a 1).
 * 
 *         In some games, users are required to roll dice of different types and
 *         then add, subtract, or otherwise manipulate the values. For instance,
 *         4D6 + 1D4 means "Add the value of a 4D6 roll to the value of a 1D4
 *         roll". The minimum and maximum values possible in this case are
 *         respectively 5, and 28.
 * 
 */
public class DiceRoller {

	static final Set<Integer> supportedDiceSide = new HashSet<Integer>(Arrays.asList(4, 6, 8, 10, 12, 20));

	public static void main(String[] args) {

		// argument should provided
		if (args.length == 0) {
			throw new IllegalArgumentException(
					"Invalid parameters. Command line argument array size : " + (args != null ? args.length : null));
		}
		try {
			int output = roll(getCorrectArguments(args).toArray());
			System.out.println("Dice Roller Output: " + output);
			// for test assert set system property
			System.setProperty("output", String.valueOf(output));
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Method to calculate dice rolls.
	 * 
	 * @param args
	 * @return
	 */
	private static int roll(Object[] args) {
		// Separated arguments should not even
		if (args.length % 2 == 0) {
			throw new IllegalArgumentException("Invalid arguments, arguments should be a valid arithmetic operator.");
		}

		// stack for operands
		Stack<Integer> diceRoll = new Stack<>();
		// stack for operators
		Stack<Character> operator = new Stack<>();

		for (int index = 1; index <= args.length; index++) {
			String arg = (String) args[index - 1];

			// on even index operator should meet
			if (index % 2 == 0) {
				if (arg.length() != 1 || !(arg.equalsIgnoreCase("+") || arg.equalsIgnoreCase("-"))) {
					throw new IllegalArgumentException(
							"Invlid Arguments, invalid operator found. supported: + or -, found: " + arg);
				}
				operator.push(arg.charAt(0));
				continue;
			}

			// operand is dice digit then split and get Number of dices and number of dice's
			// sides
			String[] strList = arg.toUpperCase().split("D");
			if (strList.length != 2 || strList[0].trim().isEmpty() || strList[1].trim().isEmpty()) {
				throw new IllegalArgumentException(
						"Invalid Arguments, argument should be in form of nDs or nds. found: " + arg);
			}

			int numberOfDices = Integer.valueOf(strList[0]);
			int numberOfSide = Integer.valueOf(strList[1]);

			if (numberOfDices <= 0) {
				throw new IllegalArgumentException(
						"Invalid Argument Number of Dice should be grater than 0. found: " + arg);
			}

			if (!supportedDiceSide.contains(numberOfSide)) {
				throw new IllegalArgumentException("Invalid Argument, give number of side is not supported. supported: "
						+ supportedDiceSide + ", found: " + numberOfSide + ", argument: " + arg);
			}

			// sum the random number
			int sum = 0;
			for (int i = 0; i < numberOfDices; i++) {
				// calculate the random number with the help of Math.random()
				/*
				 * formula to get the random number of max and min number randomeNumber =
				 * Math.random() * (max - min + 1) + min
				 */
				sum += (Math.random() * numberOfSide + 1);
			}

			diceRoll.push(sum);
			// perform arithmetic operation till all operators not use
			while (!operator.isEmpty()) {
				diceRoll.push(applyOp(operator.pop(), diceRoll.pop(), diceRoll.pop()));
			}
		}

		return diceRoll.pop();
	}

	/**
	 * Method to collect all arguments separated operator, means even index will
	 * contain operator only.
	 * 
	 * @param args
	 *            list of argument
	 * @return argument list separated by operator
	 */
	private static List<String> getCorrectArguments(String[] args) {
		List<String> arguments = new ArrayList<>(args.length);
		for (String arg : args) {
			arg = arg.trim();
			if (arg.equals("+") || arg.equals("-")) {
				arguments.add(arg);
				continue;
			}

			boolean plusSign = arg.contains("+");
			if (plusSign) {
				arguments.addAll(getSignArguments(arg.split("\\+"), arg.startsWith("+"), arg.endsWith("+"), "+"));
				continue;
			}
			boolean minus = arg.contains("-");
			if (minus) {
				arguments.addAll(getSignArguments(arg.split("\\-"), arg.startsWith("-"), arg.endsWith("-"), "-"));
				continue;
			}

			if (!plusSign && !minus)
				arguments.add(arg);
		}
		return arguments;
	}

	/**
	 * 
	 * @param split
	 *            splited arguments with sign.
	 * @param isFirstSign
	 *            is String started with sign
	 * @param isLastSign
	 *            is String ended with sign
	 * @param sign
	 *            operator. supported + or -
	 * @return argument list separated by operator
	 */
	private static List<String> getSignArguments(String[] split, boolean isFirstSign, boolean isLastSign, String sign) {
		// get the opposite sign.
		String oppositeSign = sign.equals("+") ? "-" : "+";
		// collect all argument in the list
		List<String> arguments = new ArrayList<>(split.length);

		// if string started with sign then add sign in argument list
		if (isFirstSign) {
			arguments.add(sign);
		}

		// flag to check is last argument of list is operator
		boolean isSignAdded = false;

		// loop for split argument
		for (int index = 0; index < split.length; index++) {
			// element will be the operand because split happen using operator
			String operand = split[index];
			isSignAdded = false;

			// is operand again contains opposite sign(operator)
			boolean isSignAvailable = operand.contains(oppositeSign);
			if (isSignAvailable) {
				// recursive call to get the arguments separated by opposite operator
				arguments.addAll(getSignArguments(operand.split("\\" + oppositeSign), operand.startsWith(oppositeSign),
						operand.endsWith(oppositeSign), oppositeSign));

				String lastCharactor = arguments.get(arguments.size() - 1);
				// if last argument is sign do not add sign again
				if (!(lastCharactor.equals("+") || lastCharactor.equals("-"))) {
					arguments.add(sign);
					isSignAdded = true;
				}
				continue;
			}

			// is operand is not empty then add operand and sign(operator)
			if (!operand.isEmpty()) {
				arguments.add(operand);
				arguments.add(sign);
				isSignAdded = true;
			}
		}
		// if string not ended with operator and last sign added then remove that
		// operator
		if (!isLastSign && isSignAdded) {
			arguments.remove(arguments.size() - 1);
		}
		return arguments;
	}

	/*
	 * Method to perform arithmetic operation
	 */
	public static int applyOp(char op, int b, int a) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		}
		return 0;
	}

}
