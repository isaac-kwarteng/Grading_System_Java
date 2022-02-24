import java.io.*;
import java.util.ArrayList;
class grading {
    static ArrayList<Double> exams = new ArrayList<>(), assess = new ArrayList<>(), score = new ArrayList<>(), fees = new ArrayList<>();
    public static void main(String[] args) {
        int cnta = 0, Cnt = 1, prev = 1, index = 1;
        try {
            BufferedReader scan = new BufferedReader(new FileReader("Java CS2 codes/data.txt"));
            String line;
            while ((line = scan.readLine()) != null) {
                String[] stuff = line.trim().split(",");
                fees.add(Double.parseDouble(stuff[0]));
                exams.add(Double.parseDouble(stuff[1]));
                assess.add(Double.parseDouble(stuff[2]));
                score.add(exams.get(cnta) + assess.get(cnta));
                cnta++;
            }
            double max = score.get(0), min = score.get(0), ave, sum = 0, result = 0;
            char[] grading = new char[score.size()];
            BufferedWriter writer = new BufferedWriter(new FileWriter("Java CS2 codes/record.txt"));
            for (int i = 0; i < score.size(); i++) {
                if (fees.get(i) >= 100) {
                    if (score.get(i) >= 70) grading[i] = 'A';
                    else if (score.get(i) >= 60) grading[i] = 'B';
                    else if (score.get(i) >= 50) grading[i] = 'C';
                    else if (score.get(i) >= 40) grading[i] = 'D';
                    else grading[i] = 'F';
                    sum += score.get(i);
                    min = Math.min(min, score.get(i));
                    max = Math.max(max, score.get(i));
                } else {grading[i] = 'F'; score.set(i, (double) 0);}}
            ave = sum / score.size();
            for (double j : score) result += (ave - j);
            for (int n = 1; n < score.size(); n++) {
                if (score.get(n - 1).equals(score.get(n))) Cnt++;
                if (Cnt > prev) {
                    prev = Cnt;
                    index = n;
                } else Cnt = 1;}
            double mode = score.get(index);
            writer.write(String.format("%10s %10s", "Final Score" , "\tGrade\n"));
            for (int j = 0; j < score.size(); j++){
                writer.write(String.format("%10s %10s", score.get(j), grading[j]));
                writer.write("\r\n");
            }
            writer.write("Mode: "+mode+ "\nAverage: "+ave+ "\nStandard Deviation: "+ result);

            writer.close();
        }catch (IOException e) {e.printStackTrace();}}}