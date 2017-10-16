package ide;

import java.lang.System;
import java_cup.runtime.Symbol;
import javafx.scene.control.TextArea;
import javafx.application.Platform;


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 256;
	private final int YY_EOF = 257;

    public TextArea out;
    public void setOut(TextArea t) {
        out = t;
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,258,
"45:9,44:2,45,44:2,45:18,44,3,45,32,45:7,7,6,45:4,41:4,42,45:4,29,45:2,1,2,4" +
"5:2,12,17,19,14,10,24,20,21,9,26,27,18,23,16,15,8,27,13,22,27,25,28,27:3,11" +
",45:6,38,43:2,30,34,37,43:2,36,43:2,40,35,43,31,43:2,33,39,43:7,4,45,5,45:1" +
"30,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,94,
"0,1,2,1:5,3,4,1:2,5,1,6:3,1,6:2,1:2,7,8:2,9,10,11,1,12,13,14,5,15,16,17,18," +
"19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43," +
"44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,60,64,65,6,66,6" +
"7,68,69,70,71,72,73")[0];

	private int yy_nxt[][] = unpackFromString(74,46,
"1,2,21,3,4,5,6,7,8,80,85:3,87,88,85,89,90,85,91,85:2,92,85,40,85:3,93,21,9," +
"23,21,27,23,31,23,34,23,36,34,25,29,23,10,21,-1:48,11,-1:51,85,51,85:19,-1:" +
"47,23,12,-1,23:8,-1:2,23,-1:32,23:2,28,23:8,-1:2,23,-1:10,85:21,-1:25,85:8," +
"14,85:12,-1:47,23:2,-1,23:8,-1:2,23,-1:12,13,-1:7,13,-1:35,85:4,15,85:16,-1" +
":47,23:2,-1,23,12,23:6,-1:2,23,-1:20,13,-1:35,85:4,16,85:16,-1:47,23:2,-1,2" +
"3:3,24,23:4,-1:2,23,-1:10,85:21,17,-1:46,23:2,-1,23:5,12,23:2,-1:2,23,-1:10" +
",85:5,18,85:15,-1:47,23,38,-1,23:3,24,23:4,-1:2,23,-1:10,85:5,19,85:15,-1:4" +
"7,23:2,-1,23:7,32,-1:2,23,-1:10,85:21,20,-1:24,85,22,85:15,41,85:3,-1:25,85" +
":14,26,85:6,-1:25,85:3,30,85:17,-1:25,85:5,26,85:15,-1:25,85:11,26,85:9,-1:" +
"25,85:7,33,85:13,-1:25,85:6,26,85:14,-1:25,85:4,35,85:16,-1:25,85:2,26,85:1" +
"8,-1:25,85,37,85:19,-1:25,85:14,39,85:6,-1:25,85:2,42,85:18,-1:25,85,59,85:" +
"19,-1:25,86,85:5,60,85:14,-1:25,85:9,61,85:11,-1:25,85:12,43,85:8,-1:25,85:" +
"4,62,85:16,-1:25,85:5,82,85:15,-1:25,85:15,63,85:5,-1:25,85:11,64,85:9,-1:2" +
"5,85:7,65,85:13,-1:25,85:17,66,85:3,-1:25,85:8,44,85:12,-1:25,85,68,85:19,-" +
"1:25,85,45,85:19,-1:25,85:8,46,85:12,-1:25,85:18,47,85:2,-1:25,85:13,48,85:" +
"7,-1:25,85:11,71,85:4,72,85:4,-1:25,85:4,73,85:16,-1:25,85:6,74,85:14,-1:25" +
",85:7,75,85:13,-1:25,85:17,41,85:3,-1:25,85:9,76,85:11,-1:25,85:17,77,85:3," +
"-1:25,85:5,78,85:15,-1:25,85:10,79,85:10,-1:25,85:11,49,85:9,-1:25,85:11,67" +
",85:9,-1:25,85:2,50,85:18,-1:25,85:8,52,85:12,-1:25,85:5,84,85:15,-1:25,85:" +
"7,70,85:13,-1:25,85,69,85:19,-1:25,85:5,83,85:15,-1:25,85:2,53,85:18,-1:25," +
"85,54,85:19,-1:25,85:2,55,85:18,-1:25,85:10,56,85:10,-1:25,85:7,57,85:13,-1" +
":25,85:2,58,85:18,-1:25,85:4,81,85:16,-1:17");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

    {
        Platform.runLater(() -> {
            out.setText("Compilación exitosa\n");
        });
    	return new Symbol(ide.sym.EXITO, 0, 0, ide.sym.EXITO);
    }
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
    Platform.runLater(() -> {
        out.setText(out.getText()
            + "Error lexico en <" + yyline + ", " + yychar + ">, no se reconoce: " + yytext() + "\n");
    });
    yychar = 0;
}
					case -3:
						break;
					case 3:
						{
    return new Symbol(ide.sym.FIN_LINEA, yyline, yychar, yytext());
}
					case -4:
						break;
					case 4:
						{
    return new Symbol(ide.sym.LLAVE_ABRE, yyline, yychar, yytext());
}
					case -5:
						break;
					case 5:
						{
    return new Symbol(ide.sym.LLAVE_CIERRA, yyline, yychar, yytext());
}
					case -6:
						break;
					case 6:
						{
    return new Symbol(ide.sym.COMMA, yyline, yychar, yytext());
}
					case -7:
						break;
					case 7:
						{
    return new Symbol(ide.sym.SUMA, yyline, yychar, yytext());
}
					case -8:
						break;
					case 8:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -9:
						break;
					case 9:
						{
    return new Symbol(ide.sym.VARIABLE, yyline, yychar, yytext());
}
					case -10:
						break;
					case 10:
						{}
					case -11:
						break;
					case 11:
						{
    return new Symbol(ide.sym.ASIGNACION, yyline, yychar, yytext());
}
					case -12:
						break;
					case 12:
						{
    return new Symbol(ide.sym.NOTA, yyline, yychar, yytext());
}
					case -13:
						break;
					case 13:
						{
    return new Symbol(ide.sym.POSICION, yyline, yychar, yytext());
}
					case -14:
						break;
					case 14:
						{
    return new Symbol(ide.sym.FIN, yyline, yychar, yytext());
}
					case -15:
						break;
					case 15:
						{
    return new Symbol(ide.sym.TIEMPO, yyline, yychar, yytext());
}
					case -16:
						break;
					case 16:
						{
    return new Symbol(ide.sym.TIPO_PIEZA, yyline, yychar, yytext());
}
					case -17:
						break;
					case 17:
						{
    return new Symbol(ide.sym.INICIO, yyline, yychar, yytext());
}
					case -18:
						break;
					case 18:
						{
    return new Symbol(ide.sym.FUNCION_DIBUJAR, yyline, yychar, yytext());
}
					case -19:
						break;
					case 19:
						{
    return new Symbol(ide.sym.FUNCION_REPRODUCIR, yyline, yychar, yytext());
}
					case -20:
						break;
					case 20:
						{
    return new Symbol(ide.sym.SECCION_VARIABLES, yyline, yychar, yytext());
}
					case -21:
						break;
					case 21:
						{
    Platform.runLater(() -> {
        out.setText(out.getText()
            + "Error lexico en <" + yyline + ", " + yychar + ">, no se reconoce: " + yytext() + "\n");
    });
    yychar = 0;
}
					case -22:
						break;
					case 22:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -23:
						break;
					case 23:
						{
    return new Symbol(ide.sym.VARIABLE, yyline, yychar, yytext());
}
					case -24:
						break;
					case 24:
						{
    return new Symbol(ide.sym.NOTA, yyline, yychar, yytext());
}
					case -25:
						break;
					case 25:
						{
    Platform.runLater(() -> {
        out.setText(out.getText()
            + "Error lexico en <" + yyline + ", " + yychar + ">, no se reconoce: " + yytext() + "\n");
    });
    yychar = 0;
}
					case -26:
						break;
					case 26:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -27:
						break;
					case 27:
						{
    return new Symbol(ide.sym.VARIABLE, yyline, yychar, yytext());
}
					case -28:
						break;
					case 28:
						{
    return new Symbol(ide.sym.NOTA, yyline, yychar, yytext());
}
					case -29:
						break;
					case 29:
						{
    Platform.runLater(() -> {
        out.setText(out.getText()
            + "Error lexico en <" + yyline + ", " + yychar + ">, no se reconoce: " + yytext() + "\n");
    });
    yychar = 0;
}
					case -30:
						break;
					case 30:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -31:
						break;
					case 31:
						{
    return new Symbol(ide.sym.VARIABLE, yyline, yychar, yytext());
}
					case -32:
						break;
					case 32:
						{
    return new Symbol(ide.sym.NOTA, yyline, yychar, yytext());
}
					case -33:
						break;
					case 33:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -34:
						break;
					case 34:
						{
    return new Symbol(ide.sym.VARIABLE, yyline, yychar, yytext());
}
					case -35:
						break;
					case 35:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -36:
						break;
					case 36:
						{
    return new Symbol(ide.sym.VARIABLE, yyline, yychar, yytext());
}
					case -37:
						break;
					case 37:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -38:
						break;
					case 38:
						{
    return new Symbol(ide.sym.VARIABLE, yyline, yychar, yytext());
}
					case -39:
						break;
					case 39:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -40:
						break;
					case 40:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -41:
						break;
					case 41:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -42:
						break;
					case 42:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -43:
						break;
					case 43:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -44:
						break;
					case 44:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -45:
						break;
					case 45:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -46:
						break;
					case 46:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -47:
						break;
					case 47:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -48:
						break;
					case 48:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -49:
						break;
					case 49:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -50:
						break;
					case 50:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -51:
						break;
					case 51:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -52:
						break;
					case 52:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -53:
						break;
					case 53:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -54:
						break;
					case 54:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -55:
						break;
					case 55:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -56:
						break;
					case 56:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -57:
						break;
					case 57:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -58:
						break;
					case 58:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -59:
						break;
					case 59:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -60:
						break;
					case 60:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -61:
						break;
					case 61:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -62:
						break;
					case 62:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -63:
						break;
					case 63:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -64:
						break;
					case 64:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -65:
						break;
					case 65:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -66:
						break;
					case 66:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -67:
						break;
					case 67:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -68:
						break;
					case 68:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -69:
						break;
					case 69:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -70:
						break;
					case 70:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -71:
						break;
					case 71:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -72:
						break;
					case 72:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -73:
						break;
					case 73:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -74:
						break;
					case 74:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -75:
						break;
					case 75:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -76:
						break;
					case 76:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -77:
						break;
					case 77:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -78:
						break;
					case 78:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -79:
						break;
					case 79:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -80:
						break;
					case 80:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -81:
						break;
					case 81:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -82:
						break;
					case 82:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -83:
						break;
					case 83:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -84:
						break;
					case 84:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -85:
						break;
					case 85:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -86:
						break;
					case 86:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -87:
						break;
					case 87:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -88:
						break;
					case 88:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -89:
						break;
					case 89:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -90:
						break;
					case 90:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -91:
						break;
					case 91:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -92:
						break;
					case 92:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -93:
						break;
					case 93:
						{
    return new Symbol(ide.sym.SECCION_NOMBREPROGRAMA, yyline, yychar, yytext());
}
					case -94:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
