
public class JdvAlgo
{
    //******************************************************************************
    //  STATIC MEMBERS
    //******************************************************************************
    private static final int nbColumn_default = 147,  nbLine_default = 110;
    
    //******************************************************************************
    //  MEMBERS
    //******************************************************************************
    private short[][] terrain,  terrainTransitoire;
    private int nombreDeVoisins;

    //******************************************************************************
    //  CONSTRUCTORS
    //******************************************************************************
    public JdvAlgo()
    {
        this.initGrille();
    }

    public JdvAlgo(int nbColumn, int nbLine)
    {
        this.initGrille(nbColumn, nbLine);
    }

    //******************************************************************************
    //  PUBLIC METHODS
    //******************************************************************************
    public void initGrille(int nbColumn, int nbLine)
    {
        this.terrain = new short[nbColumn][nbLine];
        this.terrainTransitoire = new short[nbColumn][nbLine];

        for(int l = 0; l < this.terrain.length; l++){
            for(int c = 0; c < this.terrain[0].length; c++){
                this.terrain[l][c] = (short) (Math.random() * 1.7);
            }
        }

    }
    
    public void initGrille(){
         this.initGrille(JdvAlgo.nbColumn_default, JdvAlgo.nbLine_default);
    }

    public void setTerrain(short[][] terrain)
    {
        this.terrain = terrain;
        terrainTransitoire = new short[terrain.length][terrain[0].length];
    }
    
    public void clear(){
        for(int c = 0; c < this.terrain.length; c++){
            for(int l = 0; l < this.terrain[0].length; l++){
                this.terrain[c][l] = 0;
            }
        }
    }

    public void generationSuivante()
    {
        for(int c = 0; c < terrain.length; c++){
            for(int l = 0; l < terrain[0].length; l++){
                terrainTransitoire[c][l] = 0;
            }
        }
        
        //***************************************
        //zone thorification
        for(int c = 0; c < terrain.length; c++){
            terrain[c][0] = terrain[c][terrain[0].length - 2];
            terrain[c][terrain[0].length - 1] = terrain[c][1];
        }
        for(int l = 0; l < terrain[0].length; l++){
            terrain[0][l] = terrain[terrain.length - 2][l];
            terrain[terrain.length - 1][l] = terrain[1][l];
        }
        
        //***************************************
        //gestion thorique des 4 coins
        terrain[0][0] = terrain[terrain.length - 2][terrain[0].length - 2];
        terrain[0][terrain[0].length - 1] = terrain[terrain.length - 2][1];
        terrain[terrain.length - 1][0] = terrain[1][terrain[0].length - 2];
        terrain[terrain.length - 1][terrain[0].length - 1] = terrain[1][1];

        for(int c = 1; c < terrainTransitoire.length - 1; c++){
            for(int l = 1; l < terrainTransitoire[0].length - 1; l++){
                /*on compte le nombre de voisin de la case courante
                 *si la case a plus de 3 ou moins de 2  voisins la cellule meurt
                 *si elle a 3 voisins une cellule née ou reste en vie
                 *si la case a 2 voisin la cellule reste dans l'état actuelle
                 */
                nombreDeVoisins = 0;
                for(int decL = -1; decL <= 1; decL++){
                    for(int decC = -1; decC <= 1; decC++){
                        if(!(decL == 0 && decC == 0)){
                            if(terrain[c + decC][l + decL] == 1){
                                nombreDeVoisins++;
                            }
                        }
                    }
                }
                if(nombreDeVoisins == 2){
                    terrainTransitoire[c][l] = terrain[c][l];

                }else if(nombreDeVoisins == 3 ){
                    terrainTransitoire[c][l] = 1;

		}else if(nombreDeVoisins == 6){
		    terrainTransitoire[c][l] = (short)((terrain[c][l] == 0)? 1: 0);

                }else{
                    terrainTransitoire[c][l] = 0;

                }
            }
        }

        for(int c = 0; c < terrain.length; c++){
            for(int l = 0; l < terrain[0].length; l++){
                terrain[c][l] = terrainTransitoire[c][l];
            }
        }
        
    }//end of generationSuivante
    
    //******************************************************************************
    //  GETTERS
    //******************************************************************************   
    public short[][] getTerrain()
    {
        return this.terrain;
    }
    
}//end of class
	
