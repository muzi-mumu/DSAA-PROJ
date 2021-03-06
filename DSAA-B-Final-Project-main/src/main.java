import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class main {
    public static void main(String[] args) {
        Cell c1 = new Cell(0.5,1.5,0.5,100,"r");
        Cell c2 = new Cell(1.1,0.5,0.3, 100,"g");
        Cell c3 = new Cell(1.8,1,0.1,100,"b");
        Cell c4 = new Cell(1.8,2,0.6,100,"y");
        //Cell c5 = new Cell(1.8,2,0.6,6,"q");//检验能否识别非法颜色。
        Cell[] cells = {c1,c2,c3,c4};


//        while (true) {
//            for (int i = 0; i < cells.length; i++) {
//                move(cells[i],cells);
//                redraw(cells,100);
//            }
//            scanCells(cells);
//            changeAllColor(cells);
//            redraw(cells,100);
//        }


        StdDraw.setCanvasSize(500,500);
        StdDraw.setXscale(0,500);
        StdDraw.setYscale(0,500);
        int scale = 100;
//        c1.draw(scale);
//        c2.draw(scale);
//        c3.draw(scale);
//        c4.draw(scale);
        while(true)
        {
            redraw(cells,scale);
            scanCells(cells);
            changeAllColor(cells);
        }
    }

    private static void update(Cell[] cells)
    {
        for(int i = 0; i < cells.length;i++)
        {

        }
    }

    /**
     * 通过修改速度，
     * @param cell
     * @param crashCells
     * @return
     */
    private static double calMinDistance(Cell cell, Cell[] crashCells)
    {
        return 0;
    }

    /**
     * 有会相撞的细胞就返回true
     * @param cells
     * @return
     */
    private static boolean hasCrashCell(Cell[] cells)
    {
        if(cells == null) return false;
        else return true;
    }


    /**
     * 假设移动一步之后，返回所有可能相撞的细胞
     *
     * @param cell 当前细胞
     * @param cells 所有细胞
     * @return 所有可能相撞的细胞
     */
    private static Cell[] crashCells(Cell cell, Cell[] cells)
    {

        return null;
    }

    /**
     * 求两个细胞间的距离
     * @param c1
     * @param c2
     * @return
     */
    private static double getDistance(Cell c1, Cell c2)
    {
        return Math.sqrt(Math.pow(c1.rx-c2.rx,2)+Math.pow(c1.ry-c2.ry,2));
    }

    /**
     * Red -> Green: 不包含自身，感知范围内，>=3个Red细胞，且Red细胞的占比>70%
     * Red -> Yellow: 上一条件不满足; 感知范围内有>=1个Yellow细胞，且Yellow细胞的占比<10%
     *
     * Green -> Blue: 不包含自身，感知范围内，>=3个Green细胞，且Green细胞的占比>70%
     * Green -> Red: 上一条件不满足; 感知范围内有>=1个Red细胞，且Red细胞的占比<10%
     *
     * Blue -> Yellow: 不包含自身，感知范围内，>=3个Blue细胞，且Blue细胞的占比>70%
     * Blue -> Green: 上一条件不满足; 感知范围内有>=1个Green细胞，且Green细胞的占比<10%
     *
     * Yellow -> Blue: 不包含自身，感知范围内，>=3个Yellow细胞，且Yellow细胞的占比>70%
     * Yellow -> Green: 上一条件不满足; 感知范围内有>=1个Green细胞，且Green细胞的占比<10%
     */
    private static void changeAllColor(Cell[] cells)
    {
        for(int i = 0; i < cells.length; i++)
        {
            changeColor(cells[i]);
        }
    }

    public static void changeColor(Cell cell)
    {
        //根据4个count来变化颜色
        if(cell.color==Color.RED){
            if(cell.Rcount>=3 && (double)cell.Rcount/(cell.Rcount+cell.Gcount+cell.Bcount+cell.Ycount)>0.7)
                cell.color = Color.GREEN;
            else if(cell.Ycount>=1 && (double)cell.Ycount/(cell.Rcount+cell.Gcount+cell.Bcount+cell.Ycount)<0.1)
                cell.color = Color.YELLOW;
            //else cell.color = Color.BLUE;
        }
        else if(cell.color==Color.GREEN){
            if(cell.Gcount>=3 && (double)cell.Gcount/(cell.Rcount+cell.Gcount+cell.Bcount+cell.Ycount)>0.7)
                cell.color = Color.BLUE;
            else if(cell.Rcount>=1 && (double)cell.Rcount/(cell.Rcount+cell.Gcount+cell.Bcount+cell.Ycount)<0.1)
                cell.color = Color.RED;
            //else cell.color = Color.YELLOW;
        }
        else if(cell.color==Color.BLUE){
            if(cell.Bcount>=3 && (double)cell.Bcount/(cell.Rcount+cell.Gcount+cell.Bcount+cell.Ycount)>0.7)
                cell.color = Color.YELLOW;
            else if(cell.Gcount>=1 && (double)cell.Gcount/(cell.Rcount+cell.Gcount+cell.Bcount+cell.Ycount)<0.1)
                cell.color = Color.GREEN;
            //else cell.color = Color.RED;
        }
        else {
            if(cell.Ycount>=3 && (double)cell.Ycount/(cell.Rcount+cell.Gcount+cell.Bcount+cell.Ycount)>0.7)
                cell.color = Color.BLUE;
            else if(cell.Gcount>=1 && (double)cell.Gcount/(cell.Rcount+cell.Gcount+cell.Bcount+cell.Ycount)<0.1)
                cell.color = Color.GREEN;
            //else cell.color = Color.RED;
        }
    }

    private static void scanCells( Cell[] cells)
    {
        for(int i = 0; i < cells.length;i++){//当前cell
            //清零之前的数据
            cells[i].Rcount=0;
            cells[i].Gcount=0;
            cells[i].Bcount=0;
            cells[i].Ycount=0;
            for(int j = 0; j < cells.length;j++)//遍历所有cell
            {
                //判断cells[j]是否在cells[i]的范围内
                if(cells[i].rx-cells[i].perceptionRange <= cells[j].rx && cells[j].rx<=cells[i].rx+cells[i].perceptionRange
                        && cells[i].ry-cells[i].perceptionRange <= cells[j].ry && cells[j].ry<=cells[i].ry+cells[i].perceptionRange){
                    //记录周围cell[i]颜色数量
                    if(cells[j].color==Color.RED)
                        cells[i].Rcount++;
                    else if(cells[j].color==Color.GREEN)
                        cells[i].Gcount++;
                    else if(cells[j].color==Color.BLUE)
                        cells[i].Bcount++;
                    else
                        cells[i].Ycount++;
                }
                //不满足就continue
            }
            //减去自己
            if(cells[i].color==Color.RED)
                cells[i].Rcount --;
            else if(cells[i].color==Color.GREEN)
                cells[i].Gcount --;
            else if(cells[i].color==Color.BLUE)
                cells[i].Bcount --;
            else
                cells[i].Ycount --;
        }
    }


    /**不要求同时移动，每个细胞是轮流移动的。
     * 根据vx vy 改变 rx，ry。
     * Red：向上移动1/15
     * Green：向下移动1/15
     * Blue：向左移动1/15
     * Yellow：向右移动1/15
     * 不能有重叠，如果间距小于1/15，就移到刚好挨到就好
     * 如果有撞击就停下，障碍消失，继续移动（下一个1/15）
     * TODO: 确定移动距离，移动方向，以及判断是否移动
     */
    public static void move()
    {

    }

    public static void move(Cell cell,Cell[] cells)
    {
        Cell[] crashCell = crashCells(cell,cells);
        if(hasCrashCell(crashCell))//有相撞
            calMinDistance(cell,cells);
        else ;//正常移动
    }



    /**
     * 计算移动方向上的最小长度，如果小于1/15 以最小长度为准
     * @param cells
     * @return
     */
    private static double minLength(Cell[] cells)
    {
        return 0;
    }


    private static void redraw(Cell[] cells,int scale)
    {
        StdDraw.clear();
        for(int i = 0; i < cells.length;i++)
        {
            cells[i].move();
            StdDraw.pause(1000);
            cells[i].draw(scale);
        }
        StdDraw.show();
        StdDraw.pause(1000);
    }

}
