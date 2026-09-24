import java.util.Scanner;

public class ThreePhaseFaultAnalysis {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("        THREE PHASE FAULT ANALYSIS");
        System.out.println("==============================================");

        System.out.print("Enter system line voltage (kV): ");
        double voltageKV = scanner.nextDouble();

        System.out.print("Enter positive sequence impedance (Ohm): ");
        double z1 = scanner.nextDouble();

        System.out.print("Enter negative sequence impedance (Ohm): ");
        double z2 = scanner.nextDouble();

        System.out.print("Enter zero sequence impedance (Ohm): ");
        double z0 = scanner.nextDouble();

        if (voltageKV <= 0 || z1 <= 0 || z2 <= 0 || z0 <= 0) {
            System.out.println("Invalid input.");
            scanner.close();
            return;
        }

        double phaseVoltage = (voltageKV * 1000)
                / Math.sqrt(3);

        System.out.println("\nSelect Fault Type:");
        System.out.println("1. Three Phase Fault (LLL)");
        System.out.println("2. Line-to-Line Fault (LL)");
        System.out.println("3. Single Line-to-Ground Fault (LG)");
        System.out.println("4. Double Line-to-Ground Fault (LLG)");

        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        double faultCurrent;

        switch (choice) {

            case 1:
                /*
                 * Three-phase symmetrical fault
                 *
                 * If = Vph / Z1
                 */
                faultCurrent = phaseVoltage / z1;

                displayResult(
                        "THREE PHASE FAULT (LLL)",
                        faultCurrent
                );
                break;

            case 2:
                /*
                 * Line-to-line fault
                 *
                 * If = sqrt(3) * Vph / (Z1 + Z2)
                 */
                faultCurrent =
                        (Math.sqrt(3) * phaseVoltage)
                        / (z1 + z2);

                displayResult(
                        "LINE-TO-LINE FAULT (LL)",
                        faultCurrent
                );
                break;

            case 3:
                /*
                 * Single line-to-ground fault
                 *
                 * If = 3Vph / (Z1 + Z2 + Z0)
                 */
                faultCurrent =
                        (3 * phaseVoltage)
                        / (z1 + z2 + z0);

                displayResult(
                        "SINGLE LINE-TO-GROUND FAULT (LG)",
                        faultCurrent
                );
                break;

            case 4:
                /*
                 * Simplified double line-to-ground
                 * calculation using sequence impedances.
                 */

                double denominator =
                        z2 + (z1 * z0) / (z1 + z0);

                faultCurrent =
                        (Math.sqrt(3) * phaseVoltage)
                        / denominator;

                displayResult(
                        "DOUBLE LINE-TO-GROUND FAULT (LLG)",
                        faultCurrent
                );
                break;

            default:
                System.out.println("Invalid fault type.");
        }

        scanner.close();
    }

    private static void displayResult(
            String faultType,
            double current) {

        System.out.println("\n----------------------------------------------");
        System.out.println("                FAULT RESULT");
        System.out.println("----------------------------------------------");

        System.out.println("Fault Type       : " + faultType);

        System.out.printf(
                "Fault Current    : %.2f A%n",
                current
        );

        System.out.printf(
                "Fault Current    : %.3f kA%n",
                current / 1000
        );

        System.out.println("----------------------------------------------");
    }
}
