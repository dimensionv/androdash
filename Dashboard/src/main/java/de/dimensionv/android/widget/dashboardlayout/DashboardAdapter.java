// ////////////////////////////////////////////////////////////////////////////
//
// Author: Volkmar Seifert
// Description:
// Adapter for holding the items in the DashboardLayout.
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

/**
 * This is the adapter holding the elements in the DashboardLayout. Since it's template-based, it
 * can hold any object while ensuring type-safety, as long as the object's class is somehow derived
 * from DashboardItem. This class is a direct extension of Android's BaseAdapter class.
 *
 * @author Volkmar Seifert
 * @see android.widget.BaseAdapter
 * @see de.dimensionv.android.widget.dashboardlayout.DashboardItem
 */
public class DashboardAdapter <T extends DashboardItem> extends BaseAdapter {
  private List<T> items = null;
  private LayoutInflater inflater = null;
  private OnClickListener onClickListener = null;

  /**
   * Creates a DashboardAdapter object with items, a given inflater to process the layout of the
   * items properly and an OnClickListener so that clicks on items can be handled as the activity
   * desires.
   *
   * @see java.util.List
   * @see android.view.LayoutInflater
   * @see android.view.View.OnClickListener
   */
  public DashboardAdapter(List<T> items, LayoutInflater inflater, OnClickListener onClickListener) {
    super();
    this.items = items;
    this.inflater = inflater;
    this.onClickListener = onClickListener;
  }

  /**
   * Returns the number of elements currently in the adapter.
   *
   * @return Number of elements
   *
   * @see android.widget.Adapter#getCount()
   */
  @Override
  public int getCount() {
    return items.size();
  }

  /**
   * Return the item at the given index / position.
   *
   * @return The appropriate object representing the dashboard item.
   *
   * @see android.widget.Adapter#getItem(int)
   */
  @Override
  public Object getItem(int position) {
    return items.get(position);
  }

  /**
   * Returns the requested item's ID.
   *
   * @return ID of the requested item.
   *
   * @see android.widget.Adapter#getItemId(int)
   */
  @SuppressWarnings("unchecked")
  @Override
  public long getItemId(int position) {
    return ((T) getItem(position)).getID();
  }

  /**
   * Get a View that displays the data at the specified position in the data set. If there is
   * already a View associated, it will be reused - as long as it's the correct subclass of View.
   * (Button or derivatives of Button). If no View is associated, or if the given View is not a
   * derivative of Button, it will be discarded and a new Button-object will be created and
   * populated.
   *
   * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
   */
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Button button = null;
    if((convertView == null) || ! (convertView instanceof Button)) {
      button = (Button) inflater.inflate(R.layout.dashboard_item, parent, false);
    } else {
      button = (Button) convertView;
    }

    @SuppressWarnings("unchecked")
    T item = (T) getItem(position);

    button.setId((int) item.getID());
    button.setText(item.getText());
    button.setCompoundDrawablesWithIntrinsicBounds(null, item.getIconDrawable(), null, null);
    button.setEnabled(item.isEnabled());
    button.setOnClickListener(onClickListener);

    return button;
  }

  /**
   * Returns the list of items in this adapter.
   *
   * @return list of items
   */
  public List<T> getList() {
    return items;
  }
}
