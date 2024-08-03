package com.goal.demo.domain;

import com.goal.demo.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static com.goal.demo.domain.ProgressTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

class ProgressTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Progress.class);
        Progress progress1 = getProgressSample1();
        Progress progress2 = new Progress();
        assertThat(progress1).isNotEqualTo(progress2);

        progress2.setId(progress1.getId());
        assertThat(progress1).isEqualTo(progress2);

        progress2 = getProgressSample2();
        assertThat(progress1).isNotEqualTo(progress2);
    }
}
