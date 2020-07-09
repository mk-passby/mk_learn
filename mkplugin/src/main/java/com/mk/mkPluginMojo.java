package com.mk;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * @program: mkplugin
 * @description: maven plugin
 * @author: mk_passby
 * @create: 2019-05-19 11:18
 **/
@Mojo(name = "mktest",defaultPhase = LifecyclePhase.PACKAGE)
public class mkPluginMojo extends AbstractMojo {

  public void execute() throws MojoExecutionException, MojoFailureException {
    System.out.println("this is a plugin-test");
  }
}
