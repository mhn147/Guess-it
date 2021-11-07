package esprit.tn.sae.guessit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

        public MyViewHolder(final View view) {
            super(view);
            this.playerNameTextView = view.findViewById(R.id.playerNameTextView);
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
