// ////////////////////////////////////////////////////////////////////////////
//
// Author: Volkmar Seifert
// Description:
// The class is the actual implementation of the Layout in Dashboard style.
//
// ////////////////////////////////////////////////////////////////////////////
// License:
// // // // // // // // // // // // // // // // // // // //
// Copyright 2011-2014 Volkmar Seifert <vs@dimensionv.de>.
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// 1. Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY VOLKMAR SEIFERT AND CONTRIBUTORS ``AS IS''
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE FOUNDATION OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
// OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
// DAMAGE.
//
// The views and conclusions contained in the software and documentation are
// those of the authors and should not be interpreted as representing official
// policies, either expressed or implied, of Volkmar Seifert <vs@dimensionv.de>.
// ////////////////////////////////////////////////////////////////////////////
package de.dimensionv.android.widget.dashboardlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * The Dashboard layout is a self-organizing layout that contains items which are equally
 * distributed across the screen's real-estate assigned to the layout.
 * <p/>
 * An "item" is an object consisting of an icon and a text, which is displayed on the screen, plus
 * an ID for internal identification and usage.
 *
 * @author Volkmar Seifert
 * @version 1.0
 * @since API 1.0.0
 */
public class DashboardLayout extends ViewGroup {

  private static final int UNEVEN_GRID_PENALTY_MULTIPLIER = 10;

  private int maxChildWidth = 0;
  private int maxChildHeight = 0;
  private DashboardAdapter<? extends DashboardItem> adapter = null;
  private int desiredCols = 0;
  private int desiredRows = 0;
  private DashboardDataSetObserver observer = null;

  /**
   * Creates a new {@code DashboardLayout} object and the given {link Context}-object.
   *
   * @param context The {@code Context} object
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public DashboardLayout(Context context) {
    super(context);
  }

  /**
   * Creates a new {@code DashboardLayout} object and the given {link Context}-object.
   *
   * @param context The {@code Context} object
   * @param attrs Attributes for the new {@code DashboardLayout}.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public DashboardLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * Creates a new {@code DashboardLayout} object and the given {link Context}-object.
   *
   * @param context The {@code Context} object
   * @param attrs Attributes for the new {@code DashboardLayout}.
   * @param defStyle The style for this new {@code DashboardLayout}.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public DashboardLayout(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  /**
   * Measure the view and its content to determine the measured width and the measured height. This
   * method is invoked by measure(int, int) and should be overriden by subclasses to provide
   * accurate and efficient measurement of their contents.
   * <p/>
   * This method determines the available / assigned size of real-estate on the screen and each of
   * each of the items to be displayed.
   *
   * @param widthMeasureSpec
   *     horizontal space requirements as imposed by the parent. The requirements are encoded with
   *     View.MeasureSpec.
   * @param heightMeasureSpec
   *     vertical space requirements as imposed by the parent. The requirements are encoded with
   *     View.MeasureSpec.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    maxChildWidth = 0;
    maxChildHeight = 0;

    int heightConstraint = MeasureSpec.AT_MOST;
    if(heightMeasureSpec < 1) {
      heightConstraint = MeasureSpec.UNSPECIFIED;
    }

    // Measure once to find the maximum child size.

    int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
        MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.AT_MOST);
    int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
        MeasureSpec.getSize(heightMeasureSpec), heightConstraint);

    final int count = getChildCount();
    for(int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if(child.getVisibility() == View.GONE) {
        continue;
      }

      child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

      maxChildWidth = Math.max(maxChildWidth, child.getMeasuredWidth());
      maxChildHeight = Math.max(maxChildHeight, child.getMeasuredHeight());
    }

    // Measure again for each child to be exactly the same size.

    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
        maxChildWidth, MeasureSpec.EXACTLY);
    childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
        maxChildHeight, MeasureSpec.EXACTLY);

    for(int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if(child.getVisibility() == View.GONE) {
        continue;
      }

      child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    setMeasuredDimension(
        View.resolveSize(maxChildWidth, widthMeasureSpec),
        View.resolveSize(maxChildHeight, heightMeasureSpec));
  }

  /**
   * Called from layout when this view should assign a size and position to each of its children.
   * Derived classes with children should override this method and call layout on each of their
   * children.
   * <p/>
   * Based on the results of the onMeasure-method, this method calculates how to arrange each item
   * within the availabe / assigned area of this layout.
   *
   * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    int width = right - left;
    int height = bottom - top;

    int count = getChildCount();

    // Calculate the number of visible children.
    int visibleCount = 0;
    for(int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if(child.getVisibility() == View.GONE) {
        continue;
      }
      visibleCount++;
    }

    if(visibleCount == 0) {
      return;
    }

    // Calculate what number of rows and columns will optimize for even
    // horizontal and
    // vertical whitespace between items. Start with a 1 x N grid, then try 2 x
    // N, and so on.
    int bestSpaceDifference = Integer.MAX_VALUE;
    int spaceDifference = 0;
    int[] spaceDiff = new int[visibleCount];
    boolean haveSquare = false;
    int root = 0;

    // Horizontal and vertical space between items
    int hSpace = 0;
    int vSpace = 0;

    int cols = 1;
    int rows;

    for(int i = 0; i < visibleCount; i++) {
      cols = i + 1;
      rows = ((visibleCount - 1) / cols) + 1;
      if(cols == rows) {
        haveSquare = ((desiredCols == desiredRows) && (visibleCount == (cols * rows)));
        root = cols;
      }

      hSpace = ((width - (maxChildWidth * cols)) / (cols + 1));
      vSpace = ((height - (maxChildHeight * rows)) / (rows + 1));

      spaceDifference = Math.abs(vSpace - hSpace);
      if((rows * cols) != visibleCount) {
        spaceDifference *= DashboardLayout.UNEVEN_GRID_PENALTY_MULTIPLIER;
      }
      spaceDiff[i] = spaceDifference;
    }

    rows = 0;

    // sort values and store column
    for(int i = 0; i < visibleCount; i++) {
      if(spaceDiff[i] < bestSpaceDifference) {
        // Found a better whitespace squareness/ratio
        bestSpaceDifference = spaceDiff[i];
        cols = i + 1;
      }
      int currentCols = i + 1;
      if(currentCols == desiredCols) {
        rows = computeRows(currentCols, visibleCount);
        if((desiredRows == 0) || (rows == desiredRows)) {
          cols = currentCols;
          break;
        }
      } else if((desiredCols == 0) && (desiredRows > 0)) {
        rows = computeRows(cols, visibleCount);
        if(rows == desiredRows) {
          cols = currentCols;
          break;
        }
      }
    }

    if(haveSquare && ((cols > 1) && (cols < visibleCount))) {
      // prefer squared arrangement unless everything can be placed into a
      // single row or column
      rows = cols = root;
    } else if(rows == 0) {
      rows = computeRows(cols, visibleCount);
    }

    hSpace = ((width - (maxChildWidth * cols)) / (cols + 1));
    vSpace = ((height - (maxChildHeight * rows)) / (rows + 1));

    // Lay out children based on calculated best-fit number of rows and cols.

    // If we chose a layout that has negative horizontal or vertical space,
    // force it to zero.
    hSpace = Math.max(0, hSpace);
    vSpace = Math.max(0, vSpace);

    // Re-use width/height variables to be child width/height.
    width = (width - (hSpace * (cols + 1))) / cols;
    height = (height - (vSpace * (rows + 1))) / rows;

    int newLeft = 0;
    int newTop = 0;
    int col = 0;
    int row = 0;
    int visibleIndex = 0;
    for(int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if(child.getVisibility() == View.GONE) {
        continue;
      }

      row = visibleIndex / cols;
      col = visibleIndex % cols;

      newLeft = (hSpace * (col + 1)) + (width * col);
      newTop = (vSpace * (row + 1)) + (height * row);

      child.layout(newLeft, newTop,
          ((hSpace == 0) && (col == (cols - 1))) ? right : (newLeft + width),
          ((vSpace == 0) && (row == (rows - 1))) ? bottom : (newTop + height));
      visibleIndex++;
    }
  }

  /**
   * Compute the number of rows for a given number of columns and a given number of visible
   * objects.
   *
   * @param cols
   *     Number of columns per row
   * @param visibleCount
   *     Number of visible objects
   *
   * @return The number of rows.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  private int computeRows(int cols, int visibleCount) {
    return ((visibleCount - 1) / cols) + 1;
  }

  /**
   * Sets the adapter containing the items for the dashboard.
   *
   * @param adapter
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public void setAdapter(DashboardAdapter<? extends DashboardItem> adapter) {
    this.adapter = adapter;
    populateFromAdapter();
    adapter.registerDataSetObserver(getObserver());
  }

  /**
   * Populates the view with the items of the adapter provided through the <code>setAdapter</code>
   * method.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public void populateFromAdapter() {
    int count = adapter.getCount();
    for(int i = 0; i < count; i++) {
      // check if there's already a view at this index, and if so, try to reuse
      // it. The method getChildAt() returns null if there's no child at the
      // given position, which indicates adapter.getView() to create a new view.
      // Otherwise, the view is handed over to getView(), which attempts to
      // reuse it. Should that be impossible (e.g. due to incompatibilities
      // between View-objects), getView() discards the old view and creates a
      // new one.
      View view = getChildAt(i);
      addView(adapter.getView(i, view, this));
    }
  }

  /**
   * Returns the number of columns desired
   *
   * @return the number of desired columns
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public int getDesiredCols() {
    return desiredCols;
  }

  /**
   * Sets the number of columns desired. If possible, the arrangement-algorithm will attempt to give
   * this value preference over all other possibilities to arrange the items.
   * <p/>
   * If both, desired columns and desired rows are set, the algorithm will attempt to comply to
   * both, if possible.
   *
   * @param desiredCols
   *     the number of columns desired
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public void setDesiredCols(int desiredCols) {
    this.desiredCols = desiredCols;
  }

  /**
   * Returns the number of rows desired
   *
   * @return the number of desired rows
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public int getDesiredRows() {
    return desiredRows;
  }

  /**
   * Sets the number of rows desired. If possible, the arrangement-algorithm will attempt to give
   * this value preference over all other possibilities to arrange the items.
   * <p/>
   * If both, desired rows and desired columns are set, the algorithm will attempt to comply to
   * both, if possible.
   *
   * @param desiredRows
   *     the number of rows desired
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  public void setDesiredRows(int desiredRows) {
    this.desiredRows = desiredRows;
  }

  /**
   * Each DashboardLayout-instance holds its own, single instance of the DashboarDataSetObserver.
   * This method takes care of returning it, if it already exists, or instantiates it, if it does
   * not exist, yet. Therefore, the observer-object must never be used directly, only via this
   * method.
   *
   * @return the observer-instance of this DashboardLayout-object.
   *
   * @since Class 1.0
   * @since API 1.0.0
   */
  private DashboardDataSetObserver getObserver() {
    if(observer == null) {
      observer = new DashboardDataSetObserver(this);
    }
    return observer;
  }
}
