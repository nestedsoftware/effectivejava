package com.nestedsoftware.enums;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class GroupingExample {
    public static void main(String[] args) {
        Map<Phase, Map<Phase, Transition>> mappings = Transition.getPhaseMappings();
        for (Entry<Phase, Map<Phase, Transition>> entry : mappings.entrySet()) {
            Phase fromPhase = entry.getKey();
            System.out.println("--" + fromPhase);
            Map<Phase, Transition> toEntries = entry.getValue();
            for (Entry<Phase,Transition> toPhaseTransition : toEntries.entrySet()) {
                Phase toPhase = toPhaseTransition.getKey();
                Transition transition = toPhaseTransition.getValue();
                System.out.println("  \\--" + toPhase + ": " + transition);
            }
        }
    }
}

enum Phase {
    SOLID,
    LIQUID,
    GAS
}

enum Transition {

    MELT(Phase.SOLID, Phase.LIQUID),
    FREEZE(Phase.LIQUID, Phase.SOLID),
    BOIL(Phase.LIQUID, Phase.GAS),
    CONDENSE(Phase.GAS, Phase.LIQUID),
    SUBLIME(Phase.SOLID, Phase.GAS),
    DEPOSIT(Phase.GAS, Phase.SOLID);

    private final Phase _from;
    private final Phase _to;

    Transition(Phase from, Phase to) {
        _from = from;
        _to = to;
    }

    class TransitionTuple {
        private final Phase _toPhase;
        private final Transition _transition;

        public TransitionTuple(Phase toPhase, Transition transition) {
            _toPhase = toPhase;
            _transition = transition;
        }

        public Phase getToPhase() {
            return _toPhase;
        }

        public Transition getTransition() {
            return _transition;
        }
    }

    private static final Map<Phase, Map<Phase, Transition>> _phaseMappings =
            Stream.of(values()).collect(
                    groupingBy(
                            t -> t._from,
                            () -> new EnumMap<>(Phase.class),
                            toMap(
                                    t -> t._to,
                                    t -> t,
                                    (x, y) -> y, //replace any duplicates
                                    () -> new EnumMap<>(Phase.class))));

    private static final Map<Phase, Map<Phase, Transition>> _alternativeMappings =
            Stream.of(values()).collect(
                    groupingBy(
                            t -> t._from,
                            () -> new EnumMap<>(Phase.class),
                            groupingBy(
                                    t -> t._to,
                                    () -> new EnumMap<>(Phase.class),
                                    collectingAndThen(toList(), items -> items.get(0))
                                    ))
                            );

    public static Map<Phase, Map<Phase, Transition>> getPhaseMappings() {
        return _alternativeMappings;
    }
}



