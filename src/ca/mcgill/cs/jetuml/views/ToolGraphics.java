/*******************************************************************************
 * JetUML - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2016, 2018 by the contributors of the JetUML project.
 *
 * See: https://github.com/prmr/JetUML
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/

package ca.mcgill.cs.jetuml.views;

import ca.mcgill.cs.jetuml.geom.Line;
import ca.mcgill.cs.jetuml.geom.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * A utility class to draw various graphics for diagram handling tools.
 */
public final class ToolGraphics
{
	private static final int HANDLE_SIZE = 6; // The length in pixel of one side of the handle.
	private static final Color SELECTION_COLOR = Color.rgb(77, 115, 153);
	private static final Color SELECTION_FILL_COLOR = Color.rgb(173, 193, 214);
	private static final Color SELECTION_FILL_TRANSPARENT = Color.rgb(173, 193, 214, 0.75);
	
	private ToolGraphics() {}
	
	/**
	 * Draws a handle on pGraphics that is centered at the position
	 * (pX, pY).
	 * 
	 * @param pGraphics The graphics context on which to draw the handle.
	 * @param pX The x-coordinate of the center of the handle.
	 * @param pY The y-coordinate of the center of the handle.
	 */
	private static void drawHandle(GraphicsContext pGraphics, int pX, int pY)
	{
		Paint oldStroke = pGraphics.getStroke();
		Paint oldFill = pGraphics.getFill();
		pGraphics.setStroke(SELECTION_COLOR);
		pGraphics.strokeRect((int)(pX - HANDLE_SIZE / 2.0), (int)(pY - HANDLE_SIZE / 2.0), HANDLE_SIZE, HANDLE_SIZE);
		pGraphics.setFill(SELECTION_FILL_COLOR);
		pGraphics.fillRect((int)(pX - HANDLE_SIZE / 2.0), (int)(pY - HANDLE_SIZE / 2.0), HANDLE_SIZE, HANDLE_SIZE);
		pGraphics.setStroke(oldStroke);
		pGraphics.setFill(oldFill);
	}
	
	/**
	 * Draws four handles on pGraphics centered at the four corners of 
	 * pBounds.
	 * 
	 * @param pGraphics The graphics context on which to draw the handles.
	 * @param pBounds Defines the four points where to draw the handles
	 */
	public static void drawHandles(GraphicsContext pGraphics, Rectangle pBounds)
	{
		drawHandle(pGraphics, pBounds.getX(), pBounds.getY());
		drawHandle(pGraphics, pBounds.getX(), pBounds.getMaxY());
		drawHandle(pGraphics, pBounds.getMaxX(), pBounds.getY());
		drawHandle(pGraphics, pBounds.getMaxX(), pBounds.getMaxY());
	}
	
	/**
	 * Draws two handles on pGraphics centered at the two ends of 
	 * pBounds.
	 * 
	 * @param pGraphics The graphics context on which to draw the handles.
	 * @param pBounds Defines the two points where to draw the handles
	 */
	public static void drawHandles(GraphicsContext pGraphics, Line pBounds)
	{
		drawHandle(pGraphics, pBounds.getX1(), pBounds.getY1());
		drawHandle(pGraphics, pBounds.getX2(), pBounds.getY2());
	}
	
	/**
	 * Draws a "rubberband" line on pGraphics. A rubberband line is a straight line
	 * in the color of the selection tools.
	 * 
	 * @param pGraphics The graphics context on which to draw the line.
	 * @param pX1 The x-coordinate of the first point
	 * @param pY1 The y-coordinate of the first point
	 * @param pX2 The x-coordinate of the second point
	 * @param pY2 The y-coordinate of the second point
	 */
	public static void drawRubberband(GraphicsContext pGraphics, double pX1, double pY1, double pX2, double pY2)
	{
		Paint oldStroke = pGraphics.getStroke();
		pGraphics.setStroke(SELECTION_FILL_COLOR);
		pGraphics.strokeLine(pX1, pY1, pX2, pY2);
		pGraphics.setStroke(oldStroke);
	}
	
	/**
	 * Draws a "lasso" rectangle on pGraphics. A lasso rectangle is a semi-transparent
	 * rectangle in the color of the selection tools.
	 * 
	 * @param pGraphics The graphics context on which to draw the lasso.
	 * @param pX The x-coordinate of the origin point
	 * @param pY The y-coordinate of the origin point
	 * @param pWidth The width of the rectangle
	 * @param pHeight The height of the rectangle
	 */
	public static void drawLasso(GraphicsContext pGraphics, double pX, double pY, double pWidth, double pHeight)
	{
		Paint oldFill = pGraphics.getFill();
		Paint oldStroke = pGraphics.getStroke();
		pGraphics.setFill(SELECTION_FILL_TRANSPARENT);
		pGraphics.setStroke(SELECTION_COLOR);
		pGraphics.fillRect(pX, pY, pWidth, pHeight);
		pGraphics.strokeRect(pX, pY, pWidth, pHeight);
		pGraphics.setFill(oldFill);
		pGraphics.setStroke(oldStroke);
	}
}
