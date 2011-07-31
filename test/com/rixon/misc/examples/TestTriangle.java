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

import static junit.framework.Assert.assertEquals;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 6/12/11 - 7:27 PM
 */
public class TestTriangle {

    @Test
    public void testEquilateralTriangle()
    {
        int result = validateTrianlgeSides(1,1,1);
        assertEquals("Result is not correct",1,result);

        result = validateTrianlgeSides(-1,-1,-1);
        assertEquals("Result is not correct",-1,result);
    }

    @Test
    public void testIsocelesTriangle()
    {
        int result = validateTrianlgeSides(2,2,1);
        assertEquals("Result is not correct",2,result);

        result = validateTrianlgeSides(-2,-2,6);
        assertEquals("Result is not correct",-1,result);

        result = validateTrianlgeSides(3,3,3);
        assertEquals("Result is not correct",1,result);

    }

    @Test
    public void testScaleneTriangle()
    {
        int result = validateTrianlgeSides(3,4,5);
        assertEquals("Result is not correct",3,result);


    }

    private static int validateTrianlgeSides(int i, int i1, int i2) {
        if ((i<1) || (i1<1) || (i2<1))
            return -1;

        if (((i+i1<i2) || (i1+i2<i) || (i+i2<i1) ))
           return -1;

        if ((i == i1) && (i == i2))
        return 1;

        if ((i ==i1) || (i == i2) || (i1 == i2))
        return 2;

        return 3;
    }


}
