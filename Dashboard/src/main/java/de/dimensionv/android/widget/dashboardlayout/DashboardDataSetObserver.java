// ////////////////////////////////////////////////////////////////////////////
//
// Author: Volkmar Seifert
// Description:
// This class implements the DataSetObserver-functionality and updates the
// DashboardLayout accordingly if called.
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

import android.database.DataSetObserver;

/**
 * This class implements the DataSetObserver-functionality and updates the DashboardLayout
 * accordingly if called.
 * <p/>
 * It's use is completely internal, and you shouldn' need to bother with it.
 * <p/>
 * It receives callbacks when a data set has been changed, or made invalid in the DashboardAdapter.
 * It is a direct extension of the <code>android.database.DataSetObserver</code> class.
 *
 * @author Volkmar Seifert
 */
public class DashboardDataSetObserver extends DataSetObserver {
  private DashboardLayout layout;

  /**
   * Creates an DashboardDataSetObserver object. It is mandatory to assign a proper existing
   * DashboardLayout here.
   *
   * @param layout
   */
  public DashboardDataSetObserver(DashboardLayout layout) {
    this.layout = layout;
  }

  /**
   * This method is called when the entire data set has changed.
   */
  public void onChanged() {
    layout.removeAllViews();
    layout.populateFromAdapter();
  }

  /**
   * This method is called when the entire data becomes invalid.
   */
  public void onInvalidated() {
    layout.removeAllViews();
  }
}
