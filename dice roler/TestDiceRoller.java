/**
 * 
 */

/**
 * @author Ravi 
 * Class to Test Dice Roller for multiple usecase with random time, Random run can provide through argument otherwise run of 10 time.
 */
public class TestDiceRoller {

	public static void main(String[] args) {

		// for random run
		int forRandom = args.length == 0 ? 10 : Integer.valueOf(args[0]);

		// use loop to run same cases for multiple time
		while (forRandom-- > 0) {
			String[] args1 = "4D6 + 3D4".split(" ");
			DiceRoller.main(args1);
			int output1 = Integer.valueOf(System.getProperty("output"));
			System.setProperty("output", "");
			if (!(7 <= output1 && output1 <= 36)) {
				break;
			}

			String[] args2 = "3D8 - 2D4".split(" ");
			DiceRoller.main(args2);
			int output2 = Integer.valueOf(System.getProperty("output"));
			System.setProperty("output", "");
			if (!(-5 <= output2 && output2 <= 22)) {
				break;
			}

			String[] args3 = "3D8 - 2D4 + 4D6".split(" ");
			DiceRoller.main(args3);
			int output3 = Integer.valueOf(System.getProperty("output"));
			System.setProperty("output", "");
			if (!(-1 <= output3 && output3 <= 46)) {
				break;
			}

			String[] args4 = "4d6 + 3D4".split(" ");
			DiceRoller.main(args4);
			int output4 = Integer.valueOf(System.getProperty("output"));
			System.setProperty("output", "");
			if (!(7 <= output4 && output4 <= 36)) {
				break;
			}

			String[] args5 = "4D6 + 3D25".split(" ");
			DiceRoller.main(args5);
			if (!System.getProperty("output").isEmpty()) {
				break;
			}

			String[] args6 = "0D6 - 1D4".split(" ");
			DiceRoller.main(args6);
			if (!(System.getProperty("output").isEmpty()))
				break;

			String[] args7 = "4D11 + 3D4".split(" ");
			DiceRoller.main(args7);
			if (!(System.getProperty("output").isEmpty()))
				break;

			String[] args8 = "4 D 6 + 3D4".split(" ");
			DiceRoller.main(args8);
			if (!(System.getProperty("output").isEmpty()))
				break;

			String[] args9 = "2d4+1D20".split(" ");
			DiceRoller.main(args9);
			int output9 = Integer.valueOf(System.getProperty("output"));
			if (!(3 <= output9 && output9 <= 28))
				break;

			String[] args10 = "3D8-2D4".split(" ");
			DiceRoller.main(args10);
			int output10 = Integer.valueOf(System.getProperty("output"));
			if (!(-5 <= output10 && output10 <= 22))
				break;
			;

			String[] args11 = "3D8-2D4+4D6".split(" ");
			DiceRoller.main(args11);
			int output11 = Integer.valueOf(System.getProperty("output"));
			if (!(-1 <= output11 && output11 <= 46))
				break;

			String[] args12 = "3D8+4d6-2D4".split(" ");
			DiceRoller.main(args12);
			int output12 = Integer.valueOf(System.getProperty("output"));
			if (!(-1 <= output12 && output12 <= 46))
				break;

			String[] args13 = "3D8+4d6- 2D4".split(" ");
			DiceRoller.main(args13);
			int output13 = Integer.valueOf(System.getProperty("output"));
			assert -1 <= output13 && output13 <= 46;

			String[] args14 = "3D8 +4d6 - 2D4".split(" ");
			DiceRoller.main(args14);
			int output14 = Integer.valueOf(System.getProperty("output"));
			if (!(-1 <= output14 && output14 <= 46)) {
				break;
			}

			String[] args15 = "3D8 +4d6- 2D4".split(" ");
			DiceRoller.main(args15);
			int output15 = Integer.valueOf(System.getProperty("output"));
			if (!(-1 <= output15 && output15 <= 46)) {
				break;
			}

			String[] args16 = "3D8 +4d6-2D4".split(" ");
			DiceRoller.main(args16);
			int output16 = Integer.valueOf(System.getProperty("output"));
			if (!(-1 <= output16 && output16 <= 46)) {
				break;
			}

			String[] args17 = "3D8+4d6-2D4+1d4".split(" ");
			DiceRoller.main(args17);
			int output17 = Integer.valueOf(System.getProperty("output"));
			if (!(0 <= output17 && output17 <= 50)) {
				break;
			}

			String[] args18 = "3D8 +4d6- 2D4+1d4".split(" ");
			DiceRoller.main(args18);
			int output18 = Integer.valueOf(System.getProperty("output"));
			if (!(0 <= output18 && output18 <= 50)) {
				break;
			}

			String[] args19 = "3D8 +4d6- 2D4 +1d4".split(" ");
			DiceRoller.main(args19);
			int output19 = Integer.valueOf(System.getProperty("output"));
			if (!(0 <= output19 && output19 <= 50)) {
				break;
			}

			String[] args20 = "3D8 +4d6- 2D4 + 1d4".split(" ");
			DiceRoller.main(args20);
			int output20 = Integer.valueOf(System.getProperty("output"));
			if (!(0 <= output20 && output20 <= 50)) {
				break;
			}
		}

		if (forRandom <= 0) {
			System.out.println("Test executed successfully.");
		}
	}

}
