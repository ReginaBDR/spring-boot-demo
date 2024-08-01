package com.goal.demo.domain;

import com.goal.demo.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static com.goal.demo.domain.ContactTestSamples.*;
import static com.goal.demo.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

class ProjectTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project.class);
        Project project1 = getProjectSample1();
        Project project2 = new Project();
        assertThat(project1).isNotEqualTo(project2);

        project2.setId(project1.getId());
        assertThat(project1).isEqualTo(project2);

        project2 = getProjectSample2();
        assertThat(project1).isNotEqualTo(project2);
    }

    @Test
    void customerTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Contact contactBack = getContactRandomSampleGenerator();

        project.setCustomer(contactBack);
        assertThat(project.getCustomer()).isEqualTo(contactBack);

        project.customer(null);
        assertThat(project.getCustomer()).isNull();
    }
}
