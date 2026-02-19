import matplotlib.pyplot as plt


N_VALUES = [50000, 100000, 200000, 400000]
STRING_N_VALUES = [50000, 100000, 200000, 400000, 800000]

DATA_NS_PER_OP = {
    "Assig2B": {
        "append": [2332.64666, 2996.84958, 4442.721875, 8588.5192725],
        "delete_back": [1600.1525, 2514.80917, 3825.705625, 7583.3884375],
    },
    "Assig2BSB": {
        "append": [2223.03834, 2778.02041, 4540.899165, 8572.408645],
        "delete_back": [1998.44832, 2185.91583, 3915.90125, 7216.3991675],
        "insert_front": [1810.17916, 2336.95958, 4517.2375, 8621.8359375],
        "delete_front": [2081.6525, 2157.28333, 3909.351665, 8054.92396],
        "insert_mid": [2856.10082, 4890.09, 8863.174165, 16447.9066675],
        "delete_mid": [2875.72832, 5420.28541, 8499.639585, 16690.8846875],
    },
}

STRING_DATA_NS_PER_OP = {
    "append": [2332.64666, 2996.84958, 4442.721875, 8588.5192725, 17368.43625],
    "delete_back": [1600.1525, 2514.80917, 3825.705625, 7583.3884375, 13870.59661375],
}

OP_ORDER = [
    "append",
    "delete_back",
    "insert_front",
    "delete_front",
    "insert_mid",
    "delete_mid",
]

ASSIG2B_OPS = [
    "append",
    "delete_back",
]

OP_LABELS = {
    "append": "append",
    "delete_back": "delete back",
    "insert_front": "insert front",
    "delete_front": "delete front",
    "insert_mid": "insert mid",
    "delete_mid": "delete mid",
}

COMPARE_OPS = [
    "append",
    "delete_back",
]


def plot_dataset(ax, title, dataset, ops):
    for op in ops:
        ax.plot(N_VALUES, dataset[op], marker="o", linewidth=2, label=OP_LABELS[op])
    ax.set_title(title)
    ax.set_xlabel("N")
    ax.set_ylabel("Time per op (ns)")
    ax.grid(True, linestyle="--", alpha=0.4)
    ax.legend(ncol=2, fontsize=9)


def plot_string_vs_sb(ax):
    for op in COMPARE_OPS:
        string_series = STRING_DATA_NS_PER_OP[op][: len(N_VALUES)]
        sb_series = DATA_NS_PER_OP["Assig2BSB"][op]
        ax.plot(
            N_VALUES,
            string_series,
            marker="o",
            linewidth=2,
            label=f"String {OP_LABELS[op]}",
        )
        ax.plot(
            N_VALUES,
            sb_series,
            marker="s",
            linewidth=2,
            label=f"SB {OP_LABELS[op]}",
        )
    ax.set_title("String vs SB (common N)")
    ax.set_xlabel("N")
    ax.set_ylabel("Time per op (ns)")
    ax.grid(True, linestyle="--", alpha=0.4)
    ax.legend(ncol=2, fontsize=9)


def plot_total_time_string_vs_sb(ax):
    for op in COMPARE_OPS:
        string_per_op = STRING_DATA_NS_PER_OP[op][: len(N_VALUES)]
        sb_per_op = DATA_NS_PER_OP["Assig2BSB"][op]
        string_total = [n * t for n, t in zip(N_VALUES, string_per_op)]
        sb_total = [n * t for n, t in zip(N_VALUES, sb_per_op)]
        ax.plot(
            N_VALUES,
            string_total,
            marker="o",
            linewidth=2,
            label=f"String {OP_LABELS[op]}",
        )
        ax.plot(
            N_VALUES,
            sb_total,
            marker="s",
            linewidth=2,
            label=f"SB {OP_LABELS[op]}",
        )
    ax.set_title("String vs SB total time (common N)")
    ax.set_xlabel("N")
    ax.set_ylabel("Total time (ns)")
    ax.grid(True, linestyle="--", alpha=0.4)
    ax.legend(ncol=2, fontsize=9)


def main():
    fig, ax = plt.subplots(1, 1, figsize=(7, 5))
    plot_total_time_string_vs_sb(ax)
    fig.suptitle("Total time: String vs SB", fontsize=14)
    fig.tight_layout()
    plt.show()


if __name__ == "__main__":
    main()