package sa.reforms.tasks.quantified;

import org.mockito.junit.jupiter.MockitoExtension;
import sa.reforms.tasks.quantified.jobs.ContractedJob;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuantifiedTaskTest {

    @InjectMocks
    private QuantifiedTask quantifiedTask;

    @Mock
    private ContractedJob contractedJob;

    @Test
    void test_getPrice() {
    }

    @Test
    void test_equals() {
    }

    @Test
    void test_canEqual() {
    }

    @Test
    void test_hashCode() {
    }

    @Test
    void test_getJob() {
    }

    @Test
    void test_toString() {
    }

}