import java.util.ArrayList;
import java.util.List;

/**
 * Program TonoBersepeda2 digunakan untuk mencari kemungkinan posisi warung
 * pemberhentian terbaik
 * yang memenuhi syarat saat Tono bersepeda dengan jarak tempuh tertentu dan
 * batasan jarak istirahat.
 */
public class TonoBersepeda2 {
    /**
     * Metode utama untuk menjalankan program.
     *
     * @param args Argumen baris perintah.
     */
    public static void main(String[] args) {
        int[] posisiWarung = { 10, 25, 30, 40, 50, 75, 80, 110, 130 };
        int totalJarak = 140;
        int maksJarakIstirahat = 30;

        List<List<Integer>> kemungkinanBerhenti = new ArrayList<>();
        int jumlahBerhentiMinimum = Integer.MAX_VALUE;

        int subsets = 1 << posisiWarung.length;

        for (int i = 0; i < subsets; i++) {
            List<Integer> tempatBerhentiSaatIni = new ArrayList<>();
            int jarakSaatIni = 0;
            boolean valid = true;

            // Membentuk subset pemberhentian saat ini
            for (int j = 0; j < posisiWarung.length; j++) {
                if ((i & (1 << j)) != 0) {
                    tempatBerhentiSaatIni.add(posisiWarung[j]);
                }
            }

            if (tempatBerhentiSaatIni.isEmpty()) {
                continue; // Melanjutkan ke subset berikutnya jika tidak ada pemberhentian
            }

            // Memeriksa apakah subset pemberhentian saat ini valid
            for (int j = 0; j < tempatBerhentiSaatIni.size(); j++) {
                int jarak = tempatBerhentiSaatIni.get(j) - jarakSaatIni;

                if (jarak > maksJarakIstirahat) {
                    valid = false;
                    break;
                }

                jarakSaatIni = tempatBerhentiSaatIni.get(j);
            }

            // Memeriksa apakah jarak dari pemberhentian terakhir ke totalJarak melebihi
            // maksJarakIstirahat
            int jarakPemberhentianTerakhirKeTotal = totalJarak
                    - tempatBerhentiSaatIni.get(tempatBerhentiSaatIni.size() - 1);
            if (jarakPemberhentianTerakhirKeTotal > maksJarakIstirahat) {
                valid = false;
            }

            // Memperbarui kemungkinanBerhenti jika ditemukan jumlah pemberhentian minimum
            // baru
            if (valid && tempatBerhentiSaatIni.size() < jumlahBerhentiMinimum) {
                kemungkinanBerhenti.clear();
                kemungkinanBerhenti.add(tempatBerhentiSaatIni);
                jumlahBerhentiMinimum = tempatBerhentiSaatIni.size();
            } else if (valid && tempatBerhentiSaatIni.size() == jumlahBerhentiMinimum) {
                kemungkinanBerhenti.add(tempatBerhentiSaatIni);
            }
        }

        // Menampilkan kemungkinan posisi warung pemberhentian terbaik yang memenuhi
        // syarat
        System.out.println("Kemungkinan posisi warung pemberhentian terbaik yang memenuhi:");
        for (List<Integer> pemberhentian : kemungkinanBerhenti) {
            for (int tempatPemberhentian : pemberhentian) {
                System.out.print(tempatPemberhentian + " km ");
            }
            System.out.println();
        }
    }
}
