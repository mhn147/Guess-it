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

public class PlayersRecyclerAdapter extends RecyclerView.Adapter<PlayersRecyclerAdapter.MyViewHolder> {
    private List<Player> players;

    public PlayersRecyclerAdapter(List<Player> players) {
        this.players = players;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView playerNameTextView;
        private Button deleteButton;

        public MyViewHolder(final View view) {
            super(view);
            this.playerNameTextView = view.findViewById(R.id.playerNameTextView);
            this.deleteButton = view.findViewById(R.id.playerDeleteButton);

            this.deleteButton.setOnClickListener(v -> {
                Intent intent = new Intent("player-deleted");
                intent.putExtra("player-deleted", players.get(getAdapterPosition()).getPlayerId());
                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
            });
        }
    }

    @NonNull
    @Override
    public PlayersRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.players_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersRecyclerAdapter.MyViewHolder holder, int position) {
        String name = players.get(position).getName();
        holder.playerNameTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
