package com.goal.demo.domain;

import com.goal.demo.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static com.goal.demo.domain.ContactTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

class ContactTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contact.class);
        Contact contact1 = getContactSample1();
        Contact contact2 = new Contact();
        assertThat(contact1).isNotEqualTo(contact2);

        contact2.setId(contact1.getId());
        assertThat(contact1).isEqualTo(contact2);

        contact2 = getContactSample2();
        assertThat(contact1).isNotEqualTo(contact2);
    }
}
