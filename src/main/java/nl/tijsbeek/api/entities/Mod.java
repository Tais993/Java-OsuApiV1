package nl.tijsbeek.api.entities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public enum Mod {
    None(0),
    NoFail(1),
    Easy(2),
    TouchDevice(4),
    Hidden(8),
    HardRock(16),
    SuddenDeath(32),
    DoubleTime(64),
    Relax(128),
    HalfTime(256),
    Nightcore(512), // Only set along with DoubleTime. i.e: NC only gives 576
    Flashlight(1024),
    Autoplay(2048),
    SpunOut(4096),
    Relax2(8192),    // Autopilot
    Perfect(16384), // Only set along with SuddenDeath. i.e: PF only gives 16416
    Key4(32768),
    Key5(65536),
    Key6(131072),
    Key7(262144),
    Key8(524288),
    FadeIn(1048576),
    Random(2097152),
    Cinema(4194304),
    Target(8388608),
    Key9(16777216),
    KeyCoop(33554432),
    Key1(67108864),
    Key3(134217728),
    Key2(268435456),
    ScoreV2(536870912),
    Mirror(1073741824);

    final int bitwise;

    @Contract(pure = true)
    Mod(int i) {
        bitwise = i;
    }

    @Contract(pure = true)
    public int getBitwise() {
        return bitwise;
    }

    public static int modsToBitwise(Mod @NotNull ... mods) {
        return modsToBitwise(List.of(mods));
    }

    public static int modsToBitwise(@NotNull Collection<Mod> mods) {
        return mods.stream()
                .mapToInt(Mod::getBitwise)
                .sum();
    }

    @NotNull
    public static Set<Mod> getByBitwise(int bitwiseInt) {
        List<Mod> reversedMods = new ArrayList<>(List.of(Mod.values()));
        Collections.reverse(reversedMods);

        Set<Mod> mods = EnumSet.noneOf(Mod.class);

        AtomicInteger bitwise = new AtomicInteger(bitwiseInt);
        while (bitwise.get() != 0) {

            for (Mod reversedMod : reversedMods) {
                if (bitwise.get() >= reversedMod.getBitwise()) {
                    mods.add(reversedMod);
                    bitwise.getAndUpdate(old -> old - reversedMod.bitwise);
                }
            }
        }

        return mods;
    }
}