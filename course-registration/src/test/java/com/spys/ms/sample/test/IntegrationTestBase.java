package com.spys.ms.sample.test;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =
{
    com.spys.ms.sample.config.TestApplicationContext.class, com.spys.ms.sample.config.ApplicationContext.class
})
@Ignore
public abstract class IntegrationTestBase
{
}
