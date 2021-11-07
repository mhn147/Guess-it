package esprit.tn.sae.guessit;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import esprit.tn.sae.guessit.entities.Player;

public class MainGamePlayersRecyclerAdapter extends RecyclerView.Adapter<MainGamePlayersRecyclerAdapter.MyViewHolder> {
    private List<InGamePlayer> inGamePlayers;

    public MainGamePlayersRecyclerAdapter(List<InGamePlayer> inGamePlayers) {
        this.inGamePlayers = inGamePlayers;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mainGamePlayerNameTextView;
        private TextView scoreTextView;
        private Button increaseScoreButton;

        public MyViewHolder(final View view) {
            super(view);
            this.mainGamePlayerNameTextView = view.findViewById(R.id.mainGamePlayerNameTextView);
            this.scoreTextView = view.findViewById(R.id.scoreTextView);
            this.increaseScoreButton = view.findViewById(R.id.increaseScoreButton);

            increaseScoreButton.setOnClickListener(v -> {
                Intent intent = new Intent("player-score-increase");
                intent.putExtra("playerId", inGamePlayers.get(getAdapterPosition()).getId());
                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
            });
        }
    }

    @NonNull
    @Override
    public MainGamePlayersRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_game_players_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainGamePlayersRecyclerAdapter.MyViewHolder holder, int position) {
        String name = inGamePlayers.get(position).getName();
        holder.mainGamePlayerNameTextView.setText(name);
        String score = inGamePlayers.get(position).getCurrentScore().toString();
        holder.scoreTextView.setText(score);
    }

    @Override
    public int getItemCount() {
        return inGamePlayers.size();
    }
}
