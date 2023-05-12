package com.eukolos.cryptoexchange.dto;

public record Quote(
        String quoteAsset,
        String baseAsset,
        String quoteQty,
        String baseQty,
        String price,
        String slippage,
        String fee
) {}
