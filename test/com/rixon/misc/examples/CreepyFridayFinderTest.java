/*
 * Copyright (c) 2011. Rixon Mathew (rixonmathew@gmail.com)
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 *  following conditions are met:
 * Redistribution of source code must retain the above copyright notice, this list of conditions and the
 *  following disclaimer.
 * Redistribution in binary form must reproduce the above copyright notice, this list of conditions and the following
 *  disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of Rixon Mathew nor the names of its contributors may be used to endorse or promote products
 *  derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 *  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.rixon.misc.examples;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 6/12/11 - 7:53 PM
 */

public class CreepyFridayFinderTest {

    @Test
    public void findFridayThe13th()
    {
        Date date = nearestFridayThe13th("06/12/2011");
        //the Date should be May 13 2011 nearest to June 12 2011


    }

    private Date nearestFridayThe13th(String date) {
        return getFormattedDate(date);
        /*
           Codes
           =====
           Jan-3
           Feb-0(1 if its a leap year)
           Mar-3
           Apr-2
           May-3
           Jun-2
           Jul-3
           Aug-3
           Sep-2
           Oct-3
           Nov-2
           Dec-3

           1) For the given date Find the week day on which 13th in current month occurs
           2) Find the difference from Friday backwards and Forwards
              (e.g. if 13th in current month occurs in Monday backwards diff would be 3 and forward would be 4)
           3) Now keep summing up the numbers of the months in backward and forward direction. The sooner you find
              the LCM will be the month in which 13th occurs on a Friday
         */
        //TODO this can be extended to find 13th Friday that occurrs in Oct as well.

    }


    private static Date getFormattedDate(String dateSource) {
        DateFormat df= SimpleDateFormat.getDateInstance(DateFormat.SHORT);

        try {
            Date nearestFriday13th = df.parse("05/13/2011");
            return nearestFriday13th;
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
