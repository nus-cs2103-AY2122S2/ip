package juke.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseInput_blankInput() {
        assertEquals("", Parser.parseCommand("").get(0)[0]);
        assertEquals("", Parser.parseCommand("").get(0)[1]);
    }

    @Test
    public void parseInput_commandWithoutArgument() {
        assertEquals("aaa", Parser.parseCommand("aaa").get(0)[0]);
        assertEquals("", Parser.parseCommand("aaa").get(0)[1]);
    }

    @Test
    public void parseInput_commandWithArgument() {
        assertEquals("aaa", Parser.parseCommand("aaa bbb").get(0)[0]);
        assertEquals("bbb", Parser.parseCommand("aaa bbb").get(0)[1]);
    }

    @Test
    public void parseInput_parameterWithoutArgument() {
        assertEquals("aaa", Parser.parseCommand("aaa bbb -ccc").get(0)[0]);
        assertEquals("bbb", Parser.parseCommand("aaa bbb -ccc").get(0)[1]);
        assertEquals("ccc", Parser.parseCommand("aaa bbb -ccc").get(1)[0]);
        assertEquals("", Parser.parseCommand("aaa bbb -ccc").get(1)[1]);
    }

    @Test
    public void parseInput_parameterWithArgument() {
        assertEquals("aaa", Parser.parseCommand("aaa bbb -ccc ddd").get(0)[0]);
        assertEquals("bbb", Parser.parseCommand("aaa bbb -ccc ddd").get(0)[1]);
        assertEquals("ccc", Parser.parseCommand("aaa bbb -ccc ddd").get(1)[0]);
        assertEquals("ddd", Parser.parseCommand("aaa bbb -ccc ddd").get(1)[1]);
    }
}
