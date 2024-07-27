package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaLexer;
import org.example.antlr.LuaParser;
import org.example.temp.LuaChunkVisitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@Slf4j
@SpringBootApplication(scanBasePackages = {"org.example.temp", "org.example.luja.compiler"})
@RequiredArgsConstructor
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Compiler started");
        final File luaFile = new File("src/main/resources/test.lua");
        log.info("Reading file: {}", luaFile.getAbsolutePath());
        var inputStream = CharStreams.fromFileName(luaFile.getAbsolutePath());

        LuaLexer lexer = new LuaLexer(inputStream);
        log.info("Lexer created");

        var tokens = new CommonTokenStream(lexer);
        LuaParser parser = new LuaParser(tokens);
        log.info("Parser created");

        ParseTree tree = parser.start_();
        log.info("Got starting token: 'start_'");

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(tree);

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();
        try (var fos = new java.io.FileOutputStream("src/main/resources/GeneratedClass.class")) {
            fos.write(bytecode);
        }
    }
}