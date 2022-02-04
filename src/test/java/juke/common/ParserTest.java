package juke.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseInput_blankInput() {
        assertEquals("", Parser.parseInput("").get(0)[0]);
        assertEquals("", Parser.parseInput("").get(0)[1]);
    }
    
    @Test
    public void parseInput_commandWithoutArgument() {
        assertEquals("aaa", Parser.parseInput("aaa").get(0)[0]);
        assertEquals("", Parser.parseInput("aaa").get(0)[1]);
    }
    
    @Test
    public void parseInput_commandWithArgument() {
        assertEquals("aaa", Parser.parseInput("aaa bbb").get(0)[0]);
        assertEquals("bbb", Parser.parseInput("aaa bbb").get(0)[1]);
    }
    
    @Test
    public void parseInput_parameterWithoutArgument() {
        assertEquals("aaa", Parser.parseInput("aaa bbb -ccc").get(0)[0]);
        assertEquals("bbb", Parser.parseInput("aaa bbb -ccc").get(0)[1]);
        assertEquals("ccc", Parser.parseInput("aaa bbb -ccc").get(1)[0]);
        assertEquals("", Parser.parseInput("aaa bbb -ccc").get(1)[1]);
    }
    
    @Test
    public void parseInput_parameterWithArgument() {
        assertEquals("aaa", Parser.parseInput("aaa bbb -ccc ddd").get(0)[0]);
        assertEquals("bbb", Parser.parseInput("aaa bbb -ccc ddd").get(0)[1]);
        assertEquals("ccc", Parser.parseInput("aaa bbb -ccc ddd").get(1)[0]);
        assertEquals("ddd", Parser.parseInput("aaa bbb -ccc ddd").get(1)[1]);
    }
}
