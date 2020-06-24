package com.sbz.battleship.repository;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RulesHandler {

    @Value("${resonerDirPath}")
    private String resonerDirPath;

    @Value("${resonerPomPath}")
    private String resonerPomPath;



    public void evaluate(String drl, String ruleFileName) throws IOException, MavenInvocationException {
        this.outputRule(drl, ruleFileName);
        this.mvnRefresh();
    }

    private void outputRule(String drl, String ruleFileName) throws IOException {
        // create new file for new rule
        File dir = new File(this.resonerDirPath);
        if (!dir.exists()) dir.mkdirs();

        String fileName = ruleFileName.replaceAll(" ", "_").toLowerCase() + ".drl";

        File ruleFile = new File(this.resonerDirPath + "/" + fileName);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruleFile))) {
            bw.append(drl);
        }
    }


    public void turnOff(String ruleFileName) throws MavenInvocationException {
        this.deleteRule(ruleFileName);
        this.mvnRefresh();
    }


    private void deleteRule(String ruleFileName)  {
        String fileName = ruleFileName.replaceAll(" ", "_").toLowerCase() + ".drl";
        File ruleFile = new File(this.resonerDirPath + "/" + fileName);
        if (ruleFile.exists()) ruleFile.delete();
    }


    private void mvnRefresh() throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();

        request.setPomFile(new File(this.resonerPomPath));
        request.setGoals(Arrays.asList("clean", "install"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(System.getenv("M2_HOME")));
        invoker.execute(request);
    }
}
