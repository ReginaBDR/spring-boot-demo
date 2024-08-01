package com.goal.demo.service.dto;

import com.goal.demo.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProgressDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgressDTO.class);
        ProgressDTO progressDTO1 = new ProgressDTO();
        progressDTO1.setId(1L);
        ProgressDTO progressDTO2 = new ProgressDTO();
        assertThat(progressDTO1).isNotEqualTo(progressDTO2);
        progressDTO2.setId(progressDTO1.getId());
        assertThat(progressDTO1).isEqualTo(progressDTO2);
        progressDTO2.setId(2L);
        assertThat(progressDTO1).isNotEqualTo(progressDTO2);
        progressDTO1.setId(null);
        assertThat(progressDTO1).isNotEqualTo(progressDTO2);
    }
}
