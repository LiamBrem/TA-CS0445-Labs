public class Rec12 {
    private static final int FIND_TRIALS = 1000;
    private static final int MAX_WORD_SIZE = 10;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Rec12 <initial_size> <max_alpha>");
            return;
        }

        int initM;
        double alpha;

        try {
            initM = Integer.parseInt(args[0]);
            alpha = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Arguments must be: int double");
            return;
        }

        HashedDictionary<String, String> table = new HashedDictionary<>(initM);
        table.setLoadFactor(alpha);

        int n = (int) (alpha * initM);
        int halfAdds = n / 2;

        System.out.println("Table init size: " + initM);
        System.out.println("Max alpha value: " + alpha);
        System.out.println("Number of adds: " + n);
        System.out.println();

        ProbeStats firstAddStats = doAdds(table, halfAdds);
        System.out.println("Average probes for first " + halfAdds + " adds = " + firstAddStats.averageProbes());
        System.out.println("Max probes = " + firstAddStats.maxProbes);
        System.out.println();

        ProbeStats secondAddStats = doAdds(table, halfAdds);
        System.out.println("Average probes for second " + halfAdds + " adds = " + secondAddStats.averageProbes());
        System.out.println("Max probes = " + secondAddStats.maxProbes);
        System.out.println();

        ProbeStats findStats = doFinds(table, FIND_TRIALS);
        System.out.println("Average probes for " + FIND_TRIALS + " finds = " + findStats.averageProbes());
        System.out.println("Max probes = " + findStats.maxProbes);
    }

    private static ProbeStats doAdds(HashedDictionary<String, String> table, int targetAdds) {
        long totalProbes = 0;
        int maxProbes = 0;
        int successfulAdds = 0;

        while (successfulAdds < targetAdds) {
            String word = StringHelp.randWord(MAX_WORD_SIZE);
            String oldValue = table.add(word, word);
            int probes = table.getProbes();

            if (oldValue == null) {
                successfulAdds++;
                totalProbes += probes;
                if (probes > maxProbes) {
                    maxProbes = probes;
                }
            }
        }

        return new ProbeStats(totalProbes, maxProbes, successfulAdds);
    }

    private static ProbeStats doFinds(HashedDictionary<String, String> table, int trials) {
        long totalProbes = 0;
        int maxProbes = 0;

        for (int i = 0; i < trials; i++) {
            String word = StringHelp.randWord(MAX_WORD_SIZE);
            table.getValue(word);
            int probes = table.getProbes();

            totalProbes += probes;
            if (probes > maxProbes) {
                maxProbes = probes;
            }
        }

        return new ProbeStats(totalProbes, maxProbes, trials);
    }

    private static class ProbeStats {
        private final long totalProbes;
        private final int maxProbes;
        private final int trials;

        private ProbeStats(long totalProbes, int maxProbes, int trials) {
            this.totalProbes = totalProbes;
            this.maxProbes = maxProbes;
            this.trials = trials;
        }

        private double averageProbes() {
            if (trials == 0) {
                return 0.0;
            }
            return (double) totalProbes / trials;
        }
    }
}

