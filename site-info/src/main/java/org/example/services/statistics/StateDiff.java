package org.example.services.statistics;

import java.util.List;

public record StateDiff(List<String> disappearedPages, List<String> newPages, List<String> changedPages) {
}