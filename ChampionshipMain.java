import java.util.Scanner;

public class ChampionshipMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Quantos times participarão? ");
        int numberClubs = sc.nextInt();
        sc.nextLine();

        while (numberClubs <  2 || numberClubs > 10) {
            System.out.println("Por favor, Digite um número entre 2 e 10.");
            numberClubs = sc.nextInt();
            sc.nextLine();
        }
        
        String[] clubs = new String[numberClubs];
        int[] points = new int[numberClubs];
        int[] victories = new int[numberClubs];
        int[] draws = new int[numberClubs];
        int[] defeats = new int[numberClubs];
        int[] proGoals = new int[numberClubs];
        int[] ownGoals = new int[numberClubs];

        for (int i = 0; i < numberClubs; i++){
            System.out.println("Nome do time " + (i+1) + ": ");
            String nameClubs = sc.nextLine();

            while (true) {
                boolean duplicado = false;

                for(int j = 0; j < i; j++) {
                    if (clubs[j].equalsIgnoreCase(nameClubs)) {
                        duplicado = true;
                        break;
                    }
                }

                if (nameClubs.trim().isEmpty()) {
                    System.out.println("Nome inválido, tente novamente.");
                } else if (duplicado) {
                    System.out.println("Nome já cadastrad, tente novamente.");
                } else {
                    break;
                }

                nameClubs = sc.nextLine();
                
            }

            clubs[i] = nameClubs;
        }

        for(int i = 0; i < numberClubs; i++) {
            for(int j = i + 1; j < numberClubs; j++) {
                int homeGoals = (int)(Math.random() * 6);
                int awayGoals = (int)(Math.random() * 6);

                System.out.println(clubs[i] + " " + homeGoals + " x " + awayGoals + " " + clubs[j]);

                proGoals[i] += homeGoals;
                ownGoals[i] += awayGoals;

                proGoals[j] += awayGoals;
                ownGoals[j] += homeGoals;

                if (homeGoals > awayGoals) {  
                    points[i] += 3;
                    victories[i] += 1;
                    defeats[j] += 1;
                } else if (awayGoals > homeGoals) {
                    points[j] += 3;
                    victories[j] += 1;
                    defeats[i] += 1;
                } else {
                    points[i] += 1;
                    points[j] += 1;
                    draws[i] += 1;
                    draws[j] += 1;
                }
            }
        }

        for(int i = 0; i < numberClubs - 1; i++ ) {
            for(int j = 0; j < numberClubs - 1 - i; j++) {
                if (points[j] < points[j+1]) {
                    // Troca pontos
                    int tmpP = points[j];
                    points[j] = points[j+1];
                    points[j+1] = tmpP;

                    // Troca vitórias
                    int tmpV = victories[j];
                    victories[j] = victories[j+1];
                    victories[j+1] = tmpV;

                    // Troca empates
                    int tmpD = draws[j];
                    draws[j] = draws[j+1];
                    draws[j+1] = tmpD;

                    // Troca derrotas
                    int tmpDef = defeats[j];
                    defeats[j] = defeats[j+1];
                    defeats[j+1] = tmpDef;

                    // Troca gols feitos
                    int tmpPG = proGoals[j];
                    proGoals[j] = proGoals[j+1];
                    proGoals[j+1] = tmpPG;

                    // Troca gols sofridos
                    int tmpOG = ownGoals[j];
                    ownGoals[j] = ownGoals[j+1];
                    ownGoals[j+1] = tmpOG;

                    // Trocaa nome dos times
                    String tmpClub = clubs[j];
                    clubs[j] = clubs[j+1];
                    clubs[j+1] = tmpClub;


                }
            }
        }

        System.out.println("\n --- Tabela de Classificação ---");
        for(int i  = 0; i < numberClubs; i++) {
            System.out.printf("%s | Pts: %d | V: %d | E: %d | D: %d | GP: %d | GC: %d%n",
                    clubs[i], points[i], victories[i], draws[i], defeats[i], proGoals[i], ownGoals[i]);
        }

        sc.close();
    }
}
