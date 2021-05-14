import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

class punto2D{
	double x,y;
	
	public punto2D(double a, double b) {
		x=a;y=b;
	}
}

public class Figure {
	punto2D vecFigures[],fig1[],figMap1[],fig2[],figMap2[],figaux[],figMapAux[];
	int puntosf1[],puntosf2[];
	Color uno = new Color(247,247,247);
	Color tres = new Color(95,24,84);
	
	public Figure() {
		fig1 = new punto2D[171];
		puntosf1 = new int[] {
				331,284, 154,46, 158,54, 156,63, 158,70, 158,79, 158,83, 161,89, 174,90, 179,89, 193,86, 196,86, 210,86, 220,92, 227,102, 229,115,
				231,148, 239,152, 245,143, 259,145, 274,148, 279,151, 288,151, 288,142, 293,134, 291,116, 296,116, 313,106, 327,102, 340,99, 
				353,97, 370,101, 391,99, 399,110, 396,121, 380,119, 368,123, 353,126, 354,142, 341,144, 338,152, 334,159, 353,160, 376,159, 
				385,159, 386,152, 406,156, 419,166, 434,165, 435,174, 454,178, 460,191, 461,199, 457,214, 458,226, 430,223, 421,228, 421,236, 
				431,240, 442,231, 453,232, 467,235, 469,247, 482,255, 502,260, 518,258, 527,264, 546,273, 566,269, 589,266, 598,265, 613,270, 
				614,282, 627,291, 632,304, 640,319, 624,323, 631,336, 633,351, 629,357, 632,376, 623,381, 623,396, 617,408, 602,428, 590,424, 
				577,427, 560,430, 545,436, 466,476, 435,476, 431,492, 418,520, 404,528, 392,536, 381,545, 356,553, 339,552, 321,545, 299,538, 
				288,529, 279,516, 269,502, 264,488, 260,460, 261,448, 256,446, 245,446, 234,446, 224,444, 210,438, 202,431, 195,427, 184,424, 
				174,418, 168,420, 163,428, 152,435, 146,441, 138,446, 127,449, 114,449, 103,449, 92,447, 78,445, 71,438, 65,431, 59,423, 
				52,413, 51,402, 47,391, 43,380, 43,368, 43,359, 44,352, 48,335, 53,328, 38,326, 47,316, 54,298, 57,296, 77,292, 85,285, 102,276, 
				116,271, 119,268, 128,256, 128,249, 135,239, 130,230, 127,219, 126,210, 129,199, 130,191, 130,180, 131,167, 131,160, 129,141, 
				128,136, 123,126, 127,109, 128,102, 128,92, 131,73, 124,66, 140,60, 141,79, 143,90, 151,89, 154,46, 158,54

		};
		
		//Incicializando la figura 1
		for(int  pos=0,i=0,j=1;i<puntosf1.length-1;i+=2,pos++,j+=2)
			fig1[pos]=new punto2D(puntosf1[i],puntosf1[j]);
		
		//Mapeo de la figura 1
		figMap1 = new punto2D[fig1.length];
		for(int i=0; i<figMap1.length; i++)
			figMap1[i] = new punto2D(0,0);
		
		fig2 = new punto2D[169];
		puntosf2 = new int[] {
				225,198, 172,69, 176,63, 187,62, 197,63, 206,59, 211,59, 215,59, 222,55, 239,55, 240,67, 249,71, 263,71, 280,70, 283,62, 288,51, 301,51,
				314,56, 322,55, 333,66, 347,83, 354,96, 358,105, 363,118, 374,125, 387,133, 394,140, 407,152, 414,160, 405,162, 395,160, 382,157, 
				371,152, 351,147, 338,144, 330,142, 328,152, 330,168, 338,190, 352,188, 374,188, 373,191, 383,195, 392,199, 401,203, 407,206, 417,214, 
				422,221, 428,225, 432,231, 437,238, 442,247, 443,255, 447,265, 450,278, 449,292, 449,305, 447,316, 442,334, 440,341, 434,356, 428,366, 
				419,374, 412,380, 404,385, 398,389, 377,392, 361,392, 339,389, 325,383, 312,373, 305,357, 294,347, 288,328, 287,312, 283,292,
				283,282, 276,273, 283,249, 292,229, 289,218, 278,214, 275,208, 269,208, 266,224, 270,234, 273,244, 273,255, 269,267, 263,274, 
				253,282, 246,293, 233,306, 163,307, 154,305, 148,301, 142,302, 138,315, 132,329, 128,336, 122,346, 118,351, 112,359, 104,362, 
				96,368, 89,368, 75,372, 61,370, 48,367, 38,361, 32,352, 27,342, 22,333, 15,320, 12,305, 13,291, 13,278, 16,262, 21,246, 29,236, 37,227, 
				44,219, 56,210, 68,204, 44,188, 34,192, 26,206, 19,213, 11,215, 1,208, 9,192, 12,180, 6,180, 12,166, 18,155, 24,151, 
				26,144, 12,139, 11,131, 24,125, 21,112, 37,112, 68,114, 79,122, 100,121, 116,121, 128,128, 138,131, 148,141, 161,142, 173,137, 
				184,131, 185,130, 188,128, 193,120, 202,111, 216,105, 224,100, 240,95, 253,90, 243,77, 232,77, 218,81, 
				206,81, 195,75, 187,75, 182,74, 172,69, 176,63
		};
		
		//Incicializando la figura 2
		for(int  pos=0,i=0,j=1;i<puntosf2.length-1;i+=2,pos++,j+=2)
			fig2[pos]=new punto2D(puntosf2[i],puntosf2[j]);
		
		//Mapeo de la figura 1
		figMap2 = new punto2D[fig2.length];
		for(int i=0; i<figMap2.length; i++)
			figMap2[i] = new punto2D(0,0);
	}
	
	//MAPEO DE LAS FIGURAS CREADAS
	public void windowMapping(int xvmax, int xvmin, int yvmax, int yvmin, int xwmax, int ywmax, Graphics g,int nf) {
		double sx = (double)(xvmax - xvmin) / (double)(xwmax - 0),  sy = (double)(yvmax - yvmin) / (double)(ywmax - 0);
		
		if(nf == 1) {
			figaux = fig1; figMapAux = figMap1;
		}else {
			figaux = fig2; figMapAux = figMap2;
		}
		
		g.setColor(uno);
		for(int i=0;i<figaux.length;i++) {
			figMapAux[i].x = figaux[i].x * sx;
			figMapAux[i].y = figaux[i].y * sy;
			figMapAux[i].x += xvmin;
			figMapAux[i].y += yvmin;
		}
		for(int p1=1,p2=2;p1<figMapAux.length-1;p1+=1,p2+=1)
			g.drawLine((int)figMapAux[p1].x, (int)figMapAux[p1].y, (int)figMapAux[p2].x, (int)figMapAux[p2].y);
	}
	
	//DIBUJANDO LAS FIGURAS
	public void drawPointVector(Graphics g, int nf) {
		if(nf == 1) {
			figaux = fig1; figMapAux = figMap1;
		}else {
			figaux = fig2; figMapAux = figMap2;
		}
		g.setColor(tres);
		for(int p1=1,p2=2;p1<figaux.length-1;p1+=1,p2+=1) 
			g.drawLine((int)figaux[p1].x,(int)figaux[p1].y,(int)figaux[p2].x,(int)figaux[p2].y);
	}
	
	//DEVOLVER COORDENADAS X DE LA FIGURA
	public Point getFigureXCoordinates(int nf){
		if(nf == 1)
			figaux = fig1;
		else
			figaux = fig2;
		
		Point p = new Point((int)figaux[0].x,(int)figaux[0].x);		
		for(int i=1;i<figaux.length;i++) {
			if(figaux[i].x < p.x)
				p.x = (int) figaux[i].x;
			if(figaux[i].x > p.y)
				p.y = (int) figaux[i].x;
		}
		return p;
	}
	
	//DEVOLVER COORDENADAS Y DE LA FIGURA
		public Point getFigureYCoordinates(int nf){
			if(nf == 1)
				figaux = fig1;
			else
				figaux = fig2;
			
			Point p = new Point((int)figaux[0].y,(int)figaux[0].y);		
			for(int i=1;i<figaux.length;i++) {
				if(figaux[i].y < p.x)
					p.x = (int) figaux[i].y;
				if(figaux[i].y > p.y)
					p.y = (int) figaux[i].y;
			}
			return p;
		}
	
	/*-----  TRANSFORMACIONES PRINCIPALES DE LAS FIGURAS  -----*/
	
	//RESTAURAR FIGURA
	public void restorePoint(int nf) {
		if(nf == 1) 
			for(int  pos=0,i=0,j=1;i<puntosf1.length-1;i+=2,pos++,j+=2)
				fig1[pos]=new punto2D(puntosf1[i],puntosf1[j]);
		else
			for(int  pos=0,i=0,j=1;i<puntosf2.length-1;i+=2,pos++,j+=2)
				fig2[pos]=new punto2D(puntosf2[i],puntosf2[j]);
	}
	
	
	//ESCALAR FIGURA
	public void scaleHPoint(double esc,int nf) {
		// xSx - TxSx + Tx,ySy - TySy + Ty
		if(nf == 1)
			figaux = fig1;
		else
			figaux = fig2;
		
		int Tx = (int)figaux[0].x, Ty = (int)figaux[0].y;
		for(punto2D fig : figaux) {
			fig.x = fig.x * esc - Tx * esc + Tx;
			fig.y = fig.y * esc - Ty * esc +Ty;
		}
	}
	
	//DEFORMAR FIGURA
	public void shearyHPoint(double A, double B,int nf) {
		//x + Ay - ATy, Bx + y - BTx, 1
		if(nf == 1)
			figaux = fig1;
		else
			figaux = fig2;
		
		int Tx = (int)figaux[0].x, Ty = (int)figaux[0].y;
		for(punto2D fig : figaux) {
			double x = fig.x;
			fig.x = x + A*fig.y - A*Ty;
			fig.y = B*x + fig.y - B*Tx;
		}
	}
	
	//ROTAR FIGURA A LA DERECHA
	public void rotateSinHPoint(int ang, int nf) {
		//xcos - ysen - Txcos + Tysen + Tx, xsen +ycos - Txsen - Tycos + Ty
		if(nf == 1)
			figaux = fig1;
		else
			figaux = fig2;
		
		double angRad = Math.toRadians(ang), seno = Math.sin(angRad), coseno=Math.cos(angRad);
		int Tx = (int)figaux[0].x, Ty = (int)figaux[0].y;
		for(punto2D fig : figaux) {
			double x = fig.x;
			fig.x = x * coseno - fig.y * seno - Tx * coseno + Ty * seno + Tx;
			fig.y = x * seno + fig.y * coseno - Tx * seno - Ty * coseno + Ty;
		}
	}
	
	//ROTAR FIGURA A LA IZQUIERDA
	public void rotateCosHPoint(int ang, int nf) {
		//xcos + ysen - Txcos - Tysen + Tx, -xsen +ycos + Txsen - Tycos + Ty
		if(nf == 1)
			figaux = fig1;
		else
			figaux = fig2;
		
		double angRad = Math.toRadians(ang), seno = Math.sin(angRad), coseno=Math.cos(angRad);
		int Tx = (int)figaux[0].x, Ty = (int)figaux[0].y;
		for(punto2D fig : figaux) {
			double x = fig.x;
			fig.x = x * coseno + fig.y * seno - Tx * coseno - Ty * seno + Tx;
			fig.y = -x * seno + fig.y * coseno + Tx * seno - Ty * coseno + Ty;
		}
	}
	
	//TRASLADAR FIGURA
	public void movePoint(int tx, int ty, int nf) {
		if(nf == 1)
			figaux = fig1;
		else
			figaux = fig2;
		
		for(punto2D fig : figaux) {
			fig.x += tx;
			fig.y += ty;
		}
	}
	
	//REFLEJAR FIGURA
	public void reflectHPoint(int refx, int refy, int nf) {
		// xRx - TxRx + Tx, yRy - TyRy + Ty
		if(nf == 1)
			figaux = fig1;
		else
			figaux = fig2;
		
		int Tx = (int)figaux[0].x, Ty = (int)figaux[0].y;
		for(punto2D fig : figaux) {
			fig.x = fig.x * refx - Tx * refx + Tx;
			fig.y = fig.y * refy - Ty * refy +Ty;
		}
		
	}
}
