package org.example.statistics;

import java.util.Set;

public record StateDiff (Set<String> disappearedPages, Set<String> newPages, Set<String> changedPages) {}