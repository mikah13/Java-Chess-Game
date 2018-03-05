/**
 * 
 */

/**
 * @author mikah
 *
 */
public class Path {
    // dir = 0 Horizontal, dir = 1 Vertical
    public static boolean checkPath(ChessBoard b, int[] loc, int[] dest) {
        if (dest[0] == loc[0]) {
            int s = Math.min(loc[1], dest[1]);
            int e = Math.max(loc[1], dest[1]);
            for (int i = s + 1; i < e; i++) {
                if (b.getSquare(new int[] { dest[0], i }).getPiece() != null) {
                    return false;
                }
            }
        } else if (dest[1] == loc[1]) {
            int s = Math.min(loc[0], dest[0]);
            int e = Math.max(loc[0], dest[0]);
            for (int i = s + 1; i < e; i++) {
                if (b.getSquare(new int[] { i, dest[1] }).getPiece() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check the diagonal path by checking every diagonal square between the
    // location and the destination if one of them is not null then returns false
    public static boolean checkDiag(ChessBoard b, int[] loc, int[] dest) {
        int rowDif = dest[0] - loc[0];
        int colDif = dest[1] - loc[1];
        if ((rowDif > 0 && colDif < 0) || (rowDif < 0 && colDif > 0)) {
            int i = 1;
            if (rowDif < 0 && colDif > 0) {
                i = -1;
            }
            while (Math.abs(i) < Math.abs(rowDif)) {

                if (b.getSquare(new int[] { loc[0] + i, loc[1] + (0 - i) }).getPiece() != null) {
                    return false;
                }
                i += (i > 0 ? 1 : -1);
            }
        } else if ((rowDif > 0 && colDif > 0) || (rowDif < 0 && colDif < 0)) {
            int i = 1;
            if (rowDif < 0 && colDif < 0) {
                i = -1;
            }
            while (Math.abs(i) < Math.abs(rowDif)) {
                if (b.getSquare(new int[] { loc[0] + i, loc[1] + i }).getPiece() != null) {
                    return false;
                }
                i += (i > 0 ? 1 : -1);
            }
        }
        return true;
    }

}
