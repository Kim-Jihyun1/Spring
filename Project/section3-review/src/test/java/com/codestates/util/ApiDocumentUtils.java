package com.codestates.util;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

public interface ApiDocumentUtils {
    static OperationRequestPreprocessor getRequestPreProcessor() {
        return preprocessRequest(prettyPrint());    // 문서에 표시되는 JSON 포맷의 request body를 예쁘게 표현해줌
    }

    static OperationResponsePreprocessor getResponsePreProcessor() {
        return preprocessResponse(prettyPrint());   // 문서에 표시되는 JSON 포맷의 request body를 예쁘게 표현해줌
    }
}
