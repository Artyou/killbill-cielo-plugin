/*
 * Copyright 2014-2016 Groupon, Inc
 * Copyright 2014-2016 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.plugin.cielo.client.payment.builder;

import java.math.BigDecimal;
import javax.annotation.Nullable;

import org.killbill.billing.plugin.util.KillBillMoney;

import cieloecommerce.sdk.ecommerce.Payment.Currency;

public abstract class RequestBuilder<R> {

    protected R sale;

    protected RequestBuilder(final R sale) {
        this.sale = sale;
    }

    public R build() {
        return sale;
    }

    protected Long toMinorUnits(@Nullable final BigDecimal amountBD, @Nullable String currencyIsoCode) {
        if (amountBD == null) {
            return null;
        }

        if (currencyIsoCode == null) {
            currencyIsoCode = Currency.BRL.toString();
        }
        return KillBillMoney.toMinorUnits(currencyIsoCode, amountBD);
    }

    protected Long toLong(@Nullable final BigDecimal amountBD) {
        return amountBD.setScale(0,BigDecimal.ROUND_HALF_UP).longValueExact();
    }

    protected Integer toInteger(@Nullable final BigDecimal amountBD) {
        return amountBD.setScale(0,BigDecimal.ROUND_HALF_UP).intValueExact();
    }
}
